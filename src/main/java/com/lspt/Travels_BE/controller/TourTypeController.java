package com.lspt.Travels_BE.controller;

import com.lspt.Travels_BE.dto.request.ApiResponse;
import com.lspt.Travels_BE.dto.request.TourTypeRequest;
import com.lspt.Travels_BE.dto.request.UserCreateRequest;
import com.lspt.Travels_BE.dto.request.UserUpdateRequest;
import com.lspt.Travels_BE.dto.response.TourTypeResponse;
import com.lspt.Travels_BE.dto.response.UserResponse;
import com.lspt.Travels_BE.service.TourTypeService;
import com.lspt.Travels_BE.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tourTypes")
@Slf4j
@RequiredArgsConstructor
public class TourTypeController {
    @Autowired
    private TourTypeService tourTypeService;

    @PostMapping
    ApiResponse<TourTypeResponse> createTourType(@RequestBody TourTypeRequest request){
        ApiResponse<TourTypeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(tourTypeService.createTourType(request));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<TourTypeResponse>> getTourType(){
        return ApiResponse.<List<TourTypeResponse>>builder()
                .result(tourTypeService.getTourType())
                .build();
    }

    @GetMapping("/{tourTypeId}")
    ApiResponse<TourTypeResponse> getTourTypeDetail(@PathVariable String tourTypeId){
        return ApiResponse.<TourTypeResponse>builder()
                .result(tourTypeService.getTourType(tourTypeId))
                .build();
    }

    @PutMapping("/{tourTypeId}")
    ApiResponse<TourTypeResponse> updateTourType(@PathVariable String tourTypeId, @RequestBody TourTypeRequest request){
        return ApiResponse.<TourTypeResponse>builder()
                .result(tourTypeService.updateTourType(tourTypeId, request))
                .build();
    }

    @DeleteMapping("/{tourTypeId}")
    ApiResponse<String> deleteTourType(@PathVariable String tourTypeId){
        tourTypeService.deleteTourType(tourTypeId);
        return ApiResponse.<String>builder()
                .result("This tour type has been delete")
                .build();
    }
}
