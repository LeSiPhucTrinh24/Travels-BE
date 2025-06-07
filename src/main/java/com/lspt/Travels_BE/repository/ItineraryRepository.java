package com.lspt.Travels_BE.repository;

import com.lspt.Travels_BE.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, String> {
    List<Itinerary> findAllByOrderByTourIdAscDestinationIdAscDayNumberAsc();
}