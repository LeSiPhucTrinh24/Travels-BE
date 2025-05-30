package com.lspt.Travels_BE.controller;

import com.cloudinary.Api;
import com.lspt.Travels_BE.dto.request.ApiResponse;
import com.lspt.Travels_BE.dto.request.TourImageCreateRequest;
import com.lspt.Travels_BE.dto.request.TourImageUpdateRequest;
import com.lspt.Travels_BE.dto.response.TourImageResponse;
import com.lspt.Travels_BE.service.TourImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/tours/{tourId}/images")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@Slf4j
public class TourImageController {

    private final TourImageService tourImageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<TourImageResponse> createTourImage(
            @PathVariable("tourId") String tourId,
            @RequestPart("file") MultipartFile file) {

        return ApiResponse.<TourImageResponse>builder()
                .result(tourImageService.addImageToExistingTour(tourId, file))
                .build();
    }


    @PutMapping(value = "/{imageId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<TourImageResponse> updateTourImage(
            @PathVariable("tourId") String tourId,
            @PathVariable("imageId") String imageId,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        return ApiResponse.<TourImageResponse>builder()
                .result(tourImageService.updateTourImage(imageId, tourId, file))
                .build();
    }

    @GetMapping
    public ApiResponse<List<TourImageResponse>> getTourImages(@PathVariable("tourId") String tourId) {
        return ApiResponse.<List<TourImageResponse>>builder()
                .result(tourImageService.getTourImagesByTourId(tourId))
                .build();
    }

    @GetMapping("/{imageId}")
    public ApiResponse<TourImageResponse> getTourImage(
            @PathVariable("tourId") String tourId,
            @PathVariable("imageId") String imageId) {
        return ApiResponse.<TourImageResponse>builder()
                .result(tourImageService.getTourImage(imageId))
                .build();
    }

    @DeleteMapping("/{imageId}")
    public ApiResponse<String> deleteTourImage(
            @PathVariable("tourId") String tourId,
            @PathVariable("imageId") String imageId) {
        tourImageService.deleteTourImage(imageId);
        return ApiResponse.<String>builder()
                .result("Image has been deleted")
                .build();
    }
}
