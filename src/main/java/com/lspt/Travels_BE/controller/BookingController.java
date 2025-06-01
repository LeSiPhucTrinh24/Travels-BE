package com.lspt.Travels_BE.controller;

import com.lspt.Travels_BE.dto.request.ApiResponse;
import com.lspt.Travels_BE.dto.request.BookingCreateRequest;
import com.lspt.Travels_BE.dto.request.BookingUpdateRequest;
import com.lspt.Travels_BE.dto.response.BookingResponse;
import com.lspt.Travels_BE.dto.response.TourTypeResponse;
import com.lspt.Travels_BE.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ApiResponse<BookingResponse> createBooking(@Valid @RequestBody BookingCreateRequest request){
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.createBooking(request))
                .build();
    }

    @PutMapping(value = "/{bookingId}")
    public ApiResponse<BookingResponse> updateBooking(@PathVariable String bookingId, @RequestBody BookingUpdateRequest request){
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.updateBooking(bookingId, request))
                .build();
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<BookingResponse>> getBookingsByUser(@PathVariable String userId) {
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.getBookingsByUserId(userId))
                .build();
    }

    @GetMapping
    ApiResponse<List<BookingResponse>> getBooking(){
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.getBooking())
                .build();
    }

    @GetMapping("/{bookingId}")
    ApiResponse<BookingResponse> getBooking(@PathVariable String bookingId){
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.getBooking(bookingId))
                .build();
    }

    @DeleteMapping("/{bookingId}")
    ApiResponse<String> deleteBooking(@PathVariable String bookingId){
        bookingService.deleteBooking(bookingId);
        return ApiResponse.<String>builder()
                .result("Booking has been delete")
                .build();
    }
}
