package com.lspt.Travels_BE.mapper;

import com.lspt.Travels_BE.dto.request.TourTypeCreateRequest;
import com.lspt.Travels_BE.dto.request.TourTypeUpdateRequest;
import com.lspt.Travels_BE.dto.request.TourUpdateRequest;
import com.lspt.Travels_BE.dto.response.TourTypeResponse;
import com.lspt.Travels_BE.entity.TourType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TourTypeMapper {
    TourType toTourType(TourTypeCreateRequest request);
    void updateTourType(@MappingTarget TourType tourType, TourTypeUpdateRequest request);
    TourTypeResponse toTourTypeResponse(TourType tourType);
}
