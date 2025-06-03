package com.lspt.Travels_BE.dto.request;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourReviewCreateRequest {
    private String tourId;
    private String userId;

    @Min(value = 1, message = "Max rating must be at least 1")
    private Integer rating;

    @NotBlank(message = "content is required")
    private String content;
}
