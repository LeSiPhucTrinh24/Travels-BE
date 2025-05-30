package com.lspt.Travels_BE.service;

import com.lspt.Travels_BE.dto.request.BookingCreateRequest;
import com.lspt.Travels_BE.dto.request.BookingUpdateRequest;
import com.lspt.Travels_BE.dto.response.BookingResponse;
import com.lspt.Travels_BE.entity.Booking;
import com.lspt.Travels_BE.exception.AppException;
import com.lspt.Travels_BE.exception.ErrorCode;
import com.lspt.Travels_BE.mapper.BookingMapper;
import com.lspt.Travels_BE.repository.BookingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;

    public BookingResponse createBooking(BookingCreateRequest request){
        Booking booking = bookingMapper.toBooking(request);
        return bookingMapper.toBookingResponse(bookingRepository.save(booking));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public BookingResponse updateBooking(String bookingId, BookingUpdateRequest request){
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        bookingMapper.updateBooking(booking, request);
        return bookingMapper.toBookingResponse(bookingRepository.save(booking));
    }

    public List<BookingResponse> getBooking(){
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toBookingResponse)
                .toList();
    }
    public BookingResponse getBooking(String bookingId){
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()-> new RuntimeException("Booking not found"));
        return bookingMapper.toBookingResponse(booking);
    }

    public List<BookingResponse> getBookingsByUserId(String userId) {
        List<Booking> bookings = bookingRepository.findAllByUserId(userId);
        return bookings.stream()
                .map(bookingMapper::toBookingResponse)
                .toList();
    }

    public void deleteBooking(String bookingId){
        if(!bookingRepository.existsById(bookingId)){
            throw new AppException(ErrorCode.BOOKING_NOT_EXISTED);
        }
        bookingRepository.deleteById(bookingId);

    }
}
