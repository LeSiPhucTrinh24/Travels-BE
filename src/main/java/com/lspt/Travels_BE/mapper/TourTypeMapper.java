package com.lspt.Travels_BE.mapper;

import com.lspt.Travels_BE.dto.request.RegisterRequest;
import com.lspt.Travels_BE.dto.request.TourTypeRequest;
import com.lspt.Travels_BE.dto.request.UserCreateRequest;
import com.lspt.Travels_BE.dto.request.UserUpdateRequest;
import com.lspt.Travels_BE.dto.response.TourTypeResponse;
import com.lspt.Travels_BE.dto.response.UserResponse;
import com.lspt.Travels_BE.entity.TourType;
import com.lspt.Travels_BE.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TourTypeMapper {
    TourType toTourType(TourTypeRequest request);
    void updateTourType(@MappingTarget TourType tourType, TourTypeRequest request);
    TourTypeResponse toTourTypeResponse(TourType tourType);
}
