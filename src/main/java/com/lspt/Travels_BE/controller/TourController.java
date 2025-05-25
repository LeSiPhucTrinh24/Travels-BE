package com.lspt.Travels_BE.controller;

import com.lspt.Travels_BE.dto.request.ApiResponse;
import com.lspt.Travels_BE.dto.request.TourCreateRequest;
import com.lspt.Travels_BE.dto.request.TourUpdateRequest;
import com.lspt.Travels_BE.dto.response.TourResponse;
import com.lspt.Travels_BE.service.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/tours")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class TourController {
    @Autowired
    private TourService tourService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<TourResponse> createTour(
            @ModelAttribute TourCreateRequest request,
            @RequestPart(value = "file", required = false)MultipartFile file
            ){
        return ApiResponse.<TourResponse>builder()
                .result(tourService.createTour(request, file))
                .build();
    }

    @PutMapping(value = "/{tourId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<TourResponse> updateTour(
            @PathVariable String tourId,
            @ModelAttribute TourUpdateRequest request,
            @RequestPart(value = "file", required = false)MultipartFile file
            ){
        return ApiResponse.<TourResponse>builder()
                .result(tourService.updateTour(tourId, request, file))
                .build();
    }

    @GetMapping
    ApiResponse<List<TourResponse>> getTour(){
        return ApiResponse.<List<TourResponse>>builder()
                .result(tourService.getTour())
                .build();
    }

    @GetMapping("/{tourId}")
    ApiResponse<TourResponse>getTour(@PathVariable String tourId){
        return ApiResponse.<TourResponse>builder()
                .result(tourService.getTour(tourId))
                .build();
    }

    @DeleteMapping("/{tourId}")
    ApiResponse<String> deleteTour(@PathVariable String tourId){
        tourService.deleteTour(tourId);
        return ApiResponse.<String>builder()
                .result("Tour has been delete")
                .build();
    }
}
