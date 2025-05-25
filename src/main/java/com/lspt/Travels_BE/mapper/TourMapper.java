package com.lspt.Travels_BE.mapper;

import com.lspt.Travels_BE.dto.request.TourCreateRequest;
import com.lspt.Travels_BE.dto.request.TourUpdateRequest;
import com.lspt.Travels_BE.dto.response.TourResponse;
import com.lspt.Travels_BE.entity.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TourMapper {
    Tour toTour(TourCreateRequest request);
    void updateTour(@MappingTarget Tour tour, TourUpdateRequest request);
    TourResponse toTourResponse(Tour tour);
}
