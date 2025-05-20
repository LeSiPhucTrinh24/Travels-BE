package com.lspt.Travels_BE.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    @Size(min = 6, message = "FULLNAME_INVALID")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    @Size(min = 3, max = 255, message = "USERNAME_INVALID")
    private String userName;

    @Size(min = 6, message = "INVALID_PASSWORD")
    private String password;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^(0[3|5|7|8|9])+([0-9]{8})$",
            message = "PHONE_INVALID"
    )
    private String phone;

    private String address;

    @Size(min = 18, message = "DOB_PASSWORD")
    private LocalDate dob;
}
