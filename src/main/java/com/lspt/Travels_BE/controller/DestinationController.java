package com.lspt.Travels_BE.controller;

import com.lspt.Travels_BE.dto.request.ApiResponse;
import com.lspt.Travels_BE.dto.request.DestinationRequest;
import com.lspt.Travels_BE.dto.request.TourTypeRequest;
import com.lspt.Travels_BE.dto.response.DestinationResponse;
import com.lspt.Travels_BE.dto.response.TourTypeResponse;
import com.lspt.Travels_BE.service.DestinationService;
import com.lspt.Travels_BE.service.TourTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinations")
@Slf4j
@RequiredArgsConstructor
public class DestinationController {
    @Autowired
    private DestinationService destinationService;

    @PostMapping
    ApiResponse<DestinationResponse> createDestination(@RequestBody DestinationRequest request){
        ApiResponse<DestinationResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(destinationService.createDestination(request));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<DestinationResponse>> getDestinations(){
        return ApiResponse.<List<DestinationResponse>>builder()
                .result(destinationService.getDestinations())
                .build();
    }

    @GetMapping("/{destinationId}")
    ApiResponse<DestinationResponse> getDestinationDetail(@PathVariable String destinationId){
        return ApiResponse.<DestinationResponse>builder()
                .result(destinationService.getDestination(destinationId))
                .build();
    }

    @PutMapping("/{destinationId}")
    ApiResponse<DestinationResponse> updateDestination(@PathVariable String destinationId, @RequestBody DestinationRequest request){
        return ApiResponse.<DestinationResponse>builder()
                .result(destinationService.updateDestination(destinationId, request))
                .build();
    }

    @DeleteMapping("/{destinationId}")
    ApiResponse<String> deleteDestination(@PathVariable String destinationId){
        destinationService.deleteDestination(destinationId);
        return ApiResponse.<String>builder()
                .result("Destination has been delete")
                .build();
    }
}
