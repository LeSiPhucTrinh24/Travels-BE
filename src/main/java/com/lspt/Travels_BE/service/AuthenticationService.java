package com.lspt.Travels_BE.service;


import com.lspt.Travels_BE.dto.request.*;
import com.lspt.Travels_BE.dto.response.AuthenticationResponse;
import com.lspt.Travels_BE.dto.response.IntrospectResponse;
import com.lspt.Travels_BE.entity.InvalidatedToken;
import com.lspt.Travels_BE.entity.User;
import com.lspt.Travels_BE.enums.Role;
import com.lspt.Travels_BE.exception.AppException;
import com.lspt.Travels_BE.exception.ErrorCode;
import com.lspt.Travels_BE.mapper.UserMapper;
import com.lspt.Travels_BE.repository.InvalidatedTokenReponsitory;
import com.lspt.Travels_BE.repository.UserReponsitory;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserReponsitory userReponsitory;
    InvalidatedTokenReponsitory invalidatedTokenReponsitory;
    UserMapper userMapper;

    @NonFinal
    @Value("${signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    public IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException {
        var token = request.getToken();
        boolean isValid = true;
        try {
            verifyToken(token, false);
        }catch (AppException e){
            isValid = false;

        }
        return IntrospectResponse.builder()
                .valid(isValid).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        var user = userReponsitory.findByUserName(request.getUserName()).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));

        System.out.println("username: " + user.getUserName());
        System.out.println("password: " + user.getPassword());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!authenticated)
            throw new AppException(ErrorCode.UNAUTHORIZED);

        var token = gererateToken(user);

        return AuthenticationResponse.builder().token(token).authenticated(true)
                .userId(user.getUserId())
                .userName(user.getUserName())
                .fullName(user.getFullName())
                .address(user.getAddress())
                .phone(user.getPhone())
                .avatar(user.getAvatar())
                .roles(user.getRoles())
                .build();
    }

    public User Register(RegisterRequest request) {
        User user = userMapper.toUserRegister(request);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> role = new HashSet<>();
        role.add(Role.USER.name());

        user.setRoles(role);

        return userReponsitory.save(user);
    }

    public User ChangePassword(String userId, ChangePasswordRequest request) {
        User user = userReponsitory.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        if(!(passwordEncoder.matches(request.getOldPassword(), user.getPassword())))
            throw new AppException(ErrorCode.OLD_PASSWORD_ERROR);

        if(passwordEncoder.matches(request.getNewPassword(), user.getPassword()))
            throw new AppException(ErrorCode.NEW_AS_OLD_PASSWORD);

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        // check password có trùng với password cũ không

        return userReponsitory.save(user);
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(request.getToken(), true);

            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken =
                    InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();

            invalidatedTokenReponsitory.save(invalidatedToken);
        } catch (AppException exception){
            log.info("Token already expired");
        }

    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isRefresh)
                ? new Date(signedJWT.getJWTClaimsSet().getIssueTime()
                .toInstant().plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS).toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if(!(verified && expiryTime.after(new Date())))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        if(invalidatedTokenReponsitory.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }

    public AuthenticationResponse refreshToken(RefreshRequest request)
            throws ParseException, JOSEException {
        var signedJWT = verifyToken(request.getToken(), true);

        var jit = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryTime(expiryTime)
                .build();

        invalidatedTokenReponsitory.save(invalidatedToken);

        var username = signedJWT.getJWTClaimsSet().getSubject();

        var user = userReponsitory.findByUserName(username).orElseThrow(
                () -> new AppException(ErrorCode.UNAUTHENTICATED)
        );

        var token = gererateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .userId(user.getUserId())
                .userName(user.getUserName())
                .fullName(user.getFullName())
                .address(user.getAddress())
                .phone(user.getPhone())
                .roles(user.getRoles())
                .build();
    }

    private String gererateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issuer("lesiphuctrinh.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now()
                        .plus(VALID_DURATION, ChronoUnit.SECONDS)
                        .toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user)).build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return  jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }
    private String buildScope(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(stringJoiner::add);

        return stringJoiner.toString();
    }
}
