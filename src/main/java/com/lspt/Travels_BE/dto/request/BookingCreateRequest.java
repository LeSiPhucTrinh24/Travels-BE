package com.lspt.Travels_BE.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

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

    @NotNull(message = "Ngày đặt không được để trống")
    private LocalDateTime bookingDate;

    private boolean status;
}
