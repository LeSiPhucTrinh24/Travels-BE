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
public class TourReviewResponse {
    private String reviewId;
    private String tourId;
    private String name;
    private String userId;
    private String fullName;
    private Integer rating;
    private String content;
    private LocalDateTime reviewDate;
    private String status;
}
