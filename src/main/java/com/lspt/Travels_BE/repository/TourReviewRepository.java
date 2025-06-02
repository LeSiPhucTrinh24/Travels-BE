package com.lspt.Travels_BE.repository;
import com.lspt.Travels_BE.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourReviewRepository extends JpaRepository<Review, String> {
    List<Review> findByTourId(String tourId);
}

