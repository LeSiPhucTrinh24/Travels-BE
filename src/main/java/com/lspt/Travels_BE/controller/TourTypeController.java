package com.lspt.Travels_BE.controller;

<<<<<<< HEAD
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
=======

public class TourTypeController {
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
>>>>>>> 514b2896ae055d8306855790cfab99ad5aacc892
    @Autowired
    private TourTypeService tourTypeService;

    @PostMapping
<<<<<<< HEAD
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
=======
    ApiResponse<TourTypeResponse> createTourType(@RequestBody TourTypeRequest request){
        ApiResponse<TourTypeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(tourTypeService.createTourType(request));
        return apiResponse;
>>>>>>> 514b2896ae055d8306855790cfab99ad5aacc892
    }

    @GetMapping
    ApiResponse<List<TourTypeResponse>> getTourType(){
        return ApiResponse.<List<TourTypeResponse>>builder()
                .result(tourTypeService.getTourType())
                .build();
    }

    @GetMapping("/{tourTypeId}")
<<<<<<< HEAD
    ApiResponse<TourTypeResponse> getTourType(@PathVariable String tourTypeId){
=======
    ApiResponse<TourTypeResponse> getTourTypeDetail(@PathVariable String tourTypeId){
>>>>>>> 514b2896ae055d8306855790cfab99ad5aacc892
        return ApiResponse.<TourTypeResponse>builder()
                .result(tourTypeService.getTourType(tourTypeId))
                .build();
    }

<<<<<<< HEAD
=======
    @PutMapping("/{tourTypeId}")
    ApiResponse<TourTypeResponse> updateTourType(@PathVariable String tourTypeId, @RequestBody TourTypeRequest request){
        return ApiResponse.<TourTypeResponse>builder()
                .result(tourTypeService.updateTourType(tourTypeId, request))
                .build();
    }

>>>>>>> 514b2896ae055d8306855790cfab99ad5aacc892
    @DeleteMapping("/{tourTypeId}")
    ApiResponse<String> deleteTourType(@PathVariable String tourTypeId){
        tourTypeService.deleteTourType(tourTypeId);
        return ApiResponse.<String>builder()
<<<<<<< HEAD
                .result("TourType has been delete")
=======
                .result("This tour type has been delete")
>>>>>>> 514b2896ae055d8306855790cfab99ad5aacc892
                .build();
    }
}
