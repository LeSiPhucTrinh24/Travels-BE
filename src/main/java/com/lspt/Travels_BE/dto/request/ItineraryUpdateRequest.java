package com.lspt.Travels_BE.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItineraryUpdateRequest {
    @NotBlank(message = "Tour ID không được để trống")
    private String tourId;

    @NotBlank(message = "Tiêu đề ngày không được để trống")
    @Size(max = 100, message = "Tiêu đề ngày không được vượt quá 100 ký tự")
    private String dayTitle;

    @Min(value = 1, message = "Số ngày phải lớn hơn 0")
    private int dayNumber;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotBlank(message = "Destination ID không được để trống")
    private String destinationId;
}
