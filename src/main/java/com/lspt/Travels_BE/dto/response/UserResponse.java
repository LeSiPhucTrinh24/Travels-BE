package com.lspt.Travels_BE.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String userId;
    private String fullName;
    private String userName;
    private String phone;
    private String address;
    private String avatar;
    private LocalDate dob;
    private Set<String> roles;
}
