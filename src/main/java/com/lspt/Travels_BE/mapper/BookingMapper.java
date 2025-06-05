package com.lspt.Travels_BE.mapper;

import com.lspt.Travels_BE.dto.request.BookingCreateRequest;
import com.lspt.Travels_BE.dto.request.BookingUpdateRequest;
import com.lspt.Travels_BE.dto.request.TourTypeCreateRequest;
import com.lspt.Travels_BE.dto.response.BookingResponse;
import com.lspt.Travels_BE.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {TourMapper.class})
public interface BookingMapper {
    Booking toBooking(BookingCreateRequest request);
    void updateBooking(@MappingTarget Booking booking, BookingUpdateRequest request);
    @Mapping(source = "tour", target = "tour")
    BookingResponse toBookingResponse(Booking booking);
}
