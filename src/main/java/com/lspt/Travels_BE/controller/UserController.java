package com.lspt.Travels_BE.controller;

import com.lspt.Travels_BE.dto.request.ApiResponse;
import com.lspt.Travels_BE.dto.request.UserCreateRequest;
import com.lspt.Travels_BE.dto.request.UserUpdateRequest;
import com.lspt.Travels_BE.dto.response.UserResponse;
import com.lspt.Travels_BE.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<UserResponse> createUser(
            @ModelAttribute UserCreateRequest request,
            @RequestPart(value = "file", required = false)MultipartFile file
    ) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request, file))
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUser(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUser())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUsser(@PathVariable String userId){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    @PutMapping(value = "/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<UserResponse> updateUser(
            @PathVariable String userId,
            @ModelAttribute UserUpdateRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request, file))
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return ApiResponse.<String>builder()
                .result("User has been delete")
                .build();
    }

    //Lấy thông tin của chính mình khi truyền vào token
    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo(){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }
}
