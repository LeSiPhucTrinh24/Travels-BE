package com.lspt.Travels_BE.service;

import com.lspt.Travels_BE.dto.request.TourReviewCreateRequest;
import com.lspt.Travels_BE.dto.response.TourReviewResponse;
import com.lspt.Travels_BE.entity.Review;
import com.lspt.Travels_BE.entity.Tour;
import com.lspt.Travels_BE.entity.User;
import com.lspt.Travels_BE.enums.ReviewStatus;
import com.lspt.Travels_BE.mapper.TourReviewMapper;
import com.lspt.Travels_BE.repository.TourRepository;
import com.lspt.Travels_BE.repository.TourReviewRepository;
import com.lspt.Travels_BE.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TourReviewService {
    @Autowired
    private TourReviewRepository tourReviewRepository;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TourReviewMapper tourReviewMapper;

    public TourReviewResponse createReview(TourReviewCreateRequest request) {
        // Kiểm tra tour và user có tồn tại không
        Tour tour = tourRepository.findById(request.getTourId())
                .orElseThrow(() -> new RuntimeException("Tour không tồn tại"));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));

        Review review = tourReviewMapper.toReview(request);
        review.setReviewDate(LocalDateTime.now());
        review.setStatus(ReviewStatus.CHO_DUYET);

        Review savedReview = tourReviewRepository.save(review);
        return tourReviewMapper.toTourReviewResponse(savedReview, tour.getName(), user.getFullName());
    }

    public List<TourReviewResponse> getReviews() {
        return tourReviewRepository.findAll().stream()
                .map(review -> {
                    Tour tour = tourRepository.findById(review.getTourId())
                            .orElseThrow(() -> new RuntimeException("Tour không tồn tại"));
                    User user = userRepository.findById(review.getUserId())
                            .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
                    return tourReviewMapper.toTourReviewResponse(review, tour.getName(), user.getFullName());
                })
                .collect(Collectors.toList());
    }

    public List<TourReviewResponse> getReviewsByTourId(String tourId) {
        return tourReviewRepository.findByTourId(tourId).stream()
                .map(review -> {
                    Tour tour = tourRepository.findById(review.getTourId())
                            .orElseThrow(() -> new RuntimeException("Tour không tồn tại"));
                    User user = userRepository.findById(review.getUserId())
                            .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
                    return tourReviewMapper.toTourReviewResponse(review, tour.getName(), user.getFullName());
                })
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public TourReviewResponse updateReviewStatus(String reviewId, String trangThai) {
        Review review = tourReviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Đánh giá không tồn tại"));

        ReviewStatus status = ReviewStatus.valueOf(trangThai);
        review.setStatus(status);
        Review updatedReview = tourReviewRepository.save(review);

        Tour tour = tourRepository.findById(updatedReview.getTourId())
                .orElseThrow(() -> new RuntimeException("Tour không tồn tại"));
        User user = userRepository.findById(updatedReview.getUserId())
                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
        return tourReviewMapper.toTourReviewResponse(updatedReview, tour.getName(), user.getFullName());
    }
}
