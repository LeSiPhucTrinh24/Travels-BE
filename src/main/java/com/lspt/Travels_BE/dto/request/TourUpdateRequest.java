package com.lspt.Travels_BE.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourUpdateRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @Positive(message = "Price must be positive")
    private double price;

    @Min(value = 1, message = "Duration must be at least 1 day")
    private int duration;

    @Future(message = "Departure date must be in the future")
    private LocalDate departureDate;

    @NotBlank(message = "Departure location is required")
    private String departureLocation;

    @Min(value = 1, message = "Max people must be at least 1")
    private int maxPeople;

    private String coverImage;

    @NotBlank(message = "Tour type is required")
    private String tourTypeId;

    private boolean status;

    private boolean featured;

    @NotBlank(message = "location is required")
    private String location;
}
