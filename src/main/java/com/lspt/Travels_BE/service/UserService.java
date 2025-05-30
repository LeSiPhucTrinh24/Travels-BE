package com.lspt.Travels_BE.service;

import com.lspt.Travels_BE.dto.request.UserCreateRequest;
import com.lspt.Travels_BE.dto.request.UserUpdateRequest;
import com.lspt.Travels_BE.dto.response.UserResponse;
import com.lspt.Travels_BE.entity.User;
import com.lspt.Travels_BE.enums.Role;
import com.lspt.Travels_BE.exception.AppException;
import com.lspt.Travels_BE.exception.ErrorCode;
import com.lspt.Travels_BE.mapper.UserMapper;
import com.lspt.Travels_BE.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    UploadImageFile uploadImageFile; // Inject dịch vụ upload ảnh

    // Tạo mới người dùng
    public UserResponse createUser(UserCreateRequest request, MultipartFile file) {
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Gán role mặc định
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);

        // Upload avatar nếu có file
        if (file != null && !file.isEmpty()) {
            try {
                String avatarUrl = uploadImageFile.uploadImage(file);
                user.setAvatar(avatarUrl);
            } catch (IOException e) {
                throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
            }
        }

        return userMapper.toUserResponse(userRepository.save(user));
    }


    // Cập nhật người dùng + avatar (nếu có)
    public UserResponse updateUser(String userId, UserUpdateRequest request, MultipartFile file) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUser(user, request);

        if (file != null && !file.isEmpty()) {
            try {
                String avatarUrl = uploadImageFile.uploadImage(file);
                user.setAvatar(avatarUrl);
            } catch (IOException e) {
                log.error("Error uploading avatar", e);
                throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
            }
        }

        return userMapper.toUserResponse(userRepository.save(user));
    }

    // Lấy danh sách người dùng (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUser() {
        log.info("In method get Users");
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    // Lấy thông tin người dùng theo ID (chỉ nếu là chính mình)
    @PostAuthorize("returnObject.userName == authentication.name or hasRole('ADMIN')")
    public UserResponse getUser(String userId) {
        log.info("In method get user by userId");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserResponse(user);
    }

    // Xóa người dùng
    public void deleteUser(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        userRepository.deleteById(userId);
    }

    // Lấy thông tin của chính người dùng đang đăng nhập
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUserName(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }
}
