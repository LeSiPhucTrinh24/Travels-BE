package com.lspt.Travels_BE.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangePasswordRequest {
    private String oldPassword;

    @Size(min = 6, message = "INVALID_PASSWORD")
    private String newPassword;
}
