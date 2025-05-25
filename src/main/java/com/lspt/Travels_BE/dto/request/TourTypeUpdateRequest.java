package com.lspt.Travels_BE.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourTypeUpdateRequest {

    @NotBlank(message = "Name is required")
    private String tourTypeName;
}
