package com.lspt.Travels_BE.controller;

import com.lspt.Travels_BE.dto.request.ApiResponse;
import com.lspt.Travels_BE.dto.request.TourReviewCreateRequest;
import com.lspt.Travels_BE.dto.response.TourReviewResponse;
import com.lspt.Travels_BE.service.TourReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tours")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class TourReviewController {
    @Autowired
    private TourReviewService tourReviewService;

    @PostMapping("/{tourId}/reviews")
    public ApiResponse<TourReviewResponse> createReview(
            @PathVariable String tourId,
            @RequestBody TourReviewCreateRequest request) {
        request.setTourId(tourId);
        return ApiResponse.<TourReviewResponse>builder()
                .result(tourReviewService.createReview(request))
                .build();
    }

    @GetMapping("/{tourId}/reviews")
    public ApiResponse<List<TourReviewResponse>> getReviewsByTourId(@PathVariable String tourId) {
        return ApiResponse.<List<TourReviewResponse>>builder()
                .result(tourReviewService.getReviewsByTourId(tourId))
                .build();
    }

    @GetMapping("/reviews")
    public ApiResponse<List<TourReviewResponse>> getAllReviews() {
        return ApiResponse.<List<TourReviewResponse>>builder()
                .result(tourReviewService.getReviews())
                .build();
    }

    @PutMapping("/reviews/{reviewId}/status")
    public ApiResponse<TourReviewResponse> updateReviewStatus(
            @PathVariable String reviewId,
            @RequestParam String trangThai) {
        return ApiResponse.<TourReviewResponse>builder()
                .result(tourReviewService.updateReviewStatus(reviewId, trangThai))
                .build();
    }

    @PutMapping("/reviews/{reviewId}")
    public ApiResponse<TourReviewResponse> updateReview(
            @PathVariable String reviewId,
            @RequestBody TourReviewCreateRequest request) {
        return ApiResponse.<TourReviewResponse>builder()
                .result(tourReviewService.updateReview(reviewId, request))
                .build();
    }
}
