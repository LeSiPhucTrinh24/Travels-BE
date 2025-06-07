package com.lspt.Travels_BE.repository;

import com.lspt.Travels_BE.entity.Booking;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    @EntityGraph(attributePaths = {"tour"})
    List<Booking> findAllByUserId(String userId);
    boolean existsByUserIdAndTourId(String userId, String tourId);
}
