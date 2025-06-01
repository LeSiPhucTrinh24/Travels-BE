package com.lspt.Travels_BE.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItineraryUpdateRequest {
    @Min(value = 1, message = "dayNumber must be at least 1")
    private Integer dayNumber;

    @NotBlank(message = "dayTitle must be required")
    private String dayTitle;

    private String description;

}