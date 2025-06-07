package com.lspt.Travels_BE.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingCreateRequest {
    @NotBlank(message = "User ID không được để trống")
    private String userId;

    @NotBlank(message = "Tour ID không được để trống")
    private String tourId;

    @Min(value = 1, message = "Số lượng người phải lớn hơn hoặc bằng 1")
    private int numberOfPeople;

    @Positive(message = "Tổng giá phải lớn hơn 0")
    private double totalPrice;

    @Future(message = "Ngày khởi hành phải lớn hơn ngày hiện tại")
    private LocalDate departureDate;

    @NotNull(message = "Ngày đặt không được để trống")
    private LocalDateTime bookingDate;

    private int status;
}
