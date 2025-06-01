package com.lspt.Travels_BE.repository;

import com.lspt.Travels_BE.entity.TourImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourImageRepository extends JpaRepository<TourImage, String> {
    List<TourImage> findByTourId(String tourId);
}
