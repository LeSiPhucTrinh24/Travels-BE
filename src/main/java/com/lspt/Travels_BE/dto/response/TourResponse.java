package com.lspt.Travels_BE.dto.response;

import com.lspt.Travels_BE.entity.TourType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourResponse {
    private String tourId;
    private String name;
    private String description;
    private double price;
    private int duration;
    private LocalDate departureDate;
    private String departureLocation;
    private int maxPeople;
    private String coverImage;
    private String tourTypeId;
    private boolean status;
    private boolean featured;
    private String destination;
}
