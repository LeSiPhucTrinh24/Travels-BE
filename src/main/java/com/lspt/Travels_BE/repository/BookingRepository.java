package com.lspt.Travels_BE.repository;

import com.lspt.Travels_BE.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    List<Booking> findAllByUserId(String userId);
    boolean existsByUserIdAndTourId(String userId, String tourId);
}
