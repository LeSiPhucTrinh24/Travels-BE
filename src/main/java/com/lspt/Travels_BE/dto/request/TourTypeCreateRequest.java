package com.lspt.Travels_BE.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourTypeCreateRequest {

    @NotBlank(message = "Name is required")
    private String name;
}
