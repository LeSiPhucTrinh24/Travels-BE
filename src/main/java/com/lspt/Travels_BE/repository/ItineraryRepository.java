package com.lspt.Travels_BE.repository;

import com.lspt.Travels_BE.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItineraryRepository extends JpaRepository<Itinerary, String> {
    List<Itinerary> findAllByTourIdAndDestinationId(String tourId, String destinationId);
    List<Itinerary> findAllByOrderByTourIdAscDestinationIdAscDayNumberAsc();
}