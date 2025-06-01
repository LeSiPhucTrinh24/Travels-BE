package com.lspt.Travels_BE.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItineraryCreateRequest {
    @NotBlank(message = "tourId is required")
    private String tourId;

    @Min(value = 1, message = "dayNumber must be at least 1 day")
    private int dayNumber;

    @NotBlank(message = "dayTitle must be required")
    private String dayTitle;

    private String description;

    @NotBlank(message = "Destination is required")
    private String destinationId;

}