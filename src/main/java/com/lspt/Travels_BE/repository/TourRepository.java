package com.lspt.Travels_BE.repository;

import com.lspt.Travels_BE.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, String> {
    boolean existsByName(String name);

}
