package com.lspt.Travels_BE.controller;

import com.lspt.Travels_BE.dto.request.ApiResponse;
import com.lspt.Travels_BE.dto.request.TourTypeCreateRequest;
import com.lspt.Travels_BE.dto.request.TourTypeUpdateRequest;
import com.lspt.Travels_BE.dto.response.TourTypeResponse;
import com.lspt.Travels_BE.service.TourTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tourType")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class TourTypeController {
    @Autowired
    private TourTypeService tourTypeService;

    @PostMapping
    public ApiResponse<TourTypeResponse> createTourType(TourTypeCreateRequest request){
        return ApiResponse.<TourTypeResponse>builder()
                .result(tourTypeService.createTourType(request))
                .build();

    }

    @PutMapping(value = "/{tourTypeId}")
    public ApiResponse<TourTypeResponse> updateTourType(@PathVariable String tourTypeId, TourTypeUpdateRequest request){
        return ApiResponse.<TourTypeResponse>builder()
                .result(tourTypeService.updateTourType(tourTypeId, request))
                .build();
    }

    @GetMapping
    ApiResponse<List<TourTypeResponse>> getTourType(){
        return ApiResponse.<List<TourTypeResponse>>builder()
                .result(tourTypeService.getTourType())
                .build();
    }

    @GetMapping("/{tourTypeId}")
    ApiResponse<TourTypeResponse> getTourType(@PathVariable String tourTypeId){
        return ApiResponse.<TourTypeResponse>builder()
                .result(tourTypeService.getTourType(tourTypeId))
                .build();
    }

    @DeleteMapping("/{tourTypeId}")
    ApiResponse<String> deleteTourType(@PathVariable String tourTypeId){
        tourTypeService.deleteTourType(tourTypeId);
        return ApiResponse.<String>builder()
                .result("TourType has been delete")
                .build();
    }
}
