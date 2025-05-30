package com.lspt.Travels_BE.mapper;

import com.lspt.Travels_BE.dto.request.TourImageCreateRequest;
import com.lspt.Travels_BE.dto.request.TourImageUpdateRequest;
import com.lspt.Travels_BE.dto.response.TourImageResponse;
import com.lspt.Travels_BE.entity.TourImage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TourImageMapper {
    TourImage toTourImage(TourImageCreateRequest request);
    void updateTourImage(@MappingTarget TourImage tourImage, TourImageUpdateRequest request);
    TourImageResponse toTourImageResponse(TourImage tourImage);
}
