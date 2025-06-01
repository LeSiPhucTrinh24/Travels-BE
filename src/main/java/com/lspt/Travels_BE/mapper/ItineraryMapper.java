package com.lspt.Travels_BE.mapper;

import com.lspt.Travels_BE.dto.request.ItineraryCreateRequest;
import com.lspt.Travels_BE.dto.request.ItineraryUpdateRequest;
import com.lspt.Travels_BE.dto.response.ItineraryResponse;
import com.lspt.Travels_BE.entity.Itinerary;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ItineraryMapper {
    Itinerary toItinerary(ItineraryCreateRequest request);
    void updateItinerary(@MappingTarget Itinerary itinerary, ItineraryUpdateRequest request);
    ItineraryResponse toItineraryResponse(Itinerary itinerary);
}
