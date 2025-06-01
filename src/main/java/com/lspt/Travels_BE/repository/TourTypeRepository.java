package com.lspt.Travels_BE.repository;

import com.lspt.Travels_BE.entity.TourType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourTypeRepository extends JpaRepository<TourType, String> {
    boolean existsByTourTypeName(String name);
}
