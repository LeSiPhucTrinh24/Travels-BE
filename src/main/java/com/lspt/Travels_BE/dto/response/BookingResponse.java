package com.lspt.Travels_BE.dto.response;

import jakarta.validation.constraints.NotNull;
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
public class BookingResponse {
    private String bookingId;
    private String userId;
    private String tourId;
    private int numberOfPeople;
    private double totalPrice;
    private LocalDate departureDate;
    private LocalDateTime bookingDate;
    private  int status;

    private UserResponse user;
    private TourResponse tour;
}
