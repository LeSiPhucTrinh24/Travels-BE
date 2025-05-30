package com.lspt.Travels_BE.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private LocalDateTime bookingDate;
    private  boolean status;
}
