package com.lspt.Travels_BE.mapper;

import com.lspt.Travels_BE.dto.request.DestinationRequest;
import com.lspt.Travels_BE.dto.request.TourTypeRequest;
import com.lspt.Travels_BE.dto.response.DestinationResponse;
import com.lspt.Travels_BE.dto.response.TourTypeResponse;
import com.lspt.Travels_BE.entity.Destination;
import com.lspt.Travels_BE.entity.TourType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DestinationMapper {
    Destination toDestination(DestinationRequest request);
    void updateDestination(@MappingTarget Destination destination, DestinationRequest request);
    DestinationResponse toDestinationResponse(Destination destination);
}
