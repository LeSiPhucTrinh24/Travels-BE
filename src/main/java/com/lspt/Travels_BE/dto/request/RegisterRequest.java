package com.lspt.Travels_BE.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    @Size(min = 6, message = "FULLNAME_INVALID")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    @Size(min = 3, max = 255, message = "USERNAME_INVALID")
    private String userName;

    @Size(min = 6, message = "INVALID_PASSWORD")
    private String password;
}
