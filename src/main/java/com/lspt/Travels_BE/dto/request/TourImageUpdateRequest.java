package com.lspt.Travels_BE.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourImageUpdateRequest {

    @NotBlank(message = "Tour ID không được để trống")
    private String tourId;

    @NotBlank(message = "URL ảnh không được để trống")
    @Size(max = 500, message = "URL không được vượt quá 500 ký tự")
    private String url;
}
