package com.lspt.Travels_BE.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    String token;
    boolean authenticated;

    String userId;
    String userName;
    String fullName;
    String address;
    String avatar;
    String phone;
    String dob;
    Set<String> roles;
}
