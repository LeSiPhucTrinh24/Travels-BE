package com.lspt.Travels_BE.mapper;

import com.lspt.Travels_BE.dto.request.RegisterRequest;
import com.lspt.Travels_BE.dto.request.UserCreateRequest;
import com.lspt.Travels_BE.dto.request.UserUpdateRequest;
import com.lspt.Travels_BE.dto.response.UserResponse;
import com.lspt.Travels_BE.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
    User toUserRegister(RegisterRequest request);
    UserResponse toUserResponse(User user);
}
