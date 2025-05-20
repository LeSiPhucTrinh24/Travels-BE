package com.lspt.Travels_BE.service;

import com.lspt.Travels_BE.dto.request.UserCreateRequest;
import com.lspt.Travels_BE.dto.request.UserUpdateRequest;
import com.lspt.Travels_BE.dto.response.UserResponse;
import com.lspt.Travels_BE.entity.User;
import com.lspt.Travels_BE.enums.Role;
import com.lspt.Travels_BE.exception.AppException;
import com.lspt.Travels_BE.exception.ErrorCode;
import com.lspt.Travels_BE.mapper.UserMapper;
import com.lspt.Travels_BE.repository.UserReponsitory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserReponsitory userReponsitory;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreateRequest request){
        if(userReponsitory.existsByUserName(request.getUserName()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);

        //mã hóa mật khẩu
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        //set giá trị role mặc định
        HashSet<String> role = new HashSet<>();
        role.add(Role.USER.name());

        user.setRoles(role);

        return userMapper.toUserResponse(userReponsitory.save(user));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request){
        User user = userReponsitory.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));

        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userReponsitory.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUser(){
        log.info("In method get Users");
        return userReponsitory.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    //user chỉ lay được thông tin của chinnh mình
    @PostAuthorize("returnObject.userName == authentication.name")
    public UserResponse getUser(String userId){
        log.info("In method get user by userId");
        return userMapper.toUserResponse(userReponsitory.findById(userId).orElseThrow(()-> new RuntimeException("User not found")));
    }

    public  void deleteUser(String userId){
        userReponsitory.deleteById(userId);
    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userReponsitory.findByUserName(name).orElseThrow(
                ()-> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }
}
