package com.lspt.Travels_BE.mapper;
import com.lspt.Travels_BE.dto.request.TourReviewCreateRequest;
import com.lspt.Travels_BE.dto.response.TourReviewResponse;
import com.lspt.Travels_BE.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TourReviewMapper {
    Review toReview(TourReviewCreateRequest request);
    TourReviewResponse toTourReviewResponse(Review review, String name, String fullName);
}