package com.lspt.Travels_BE.controller;

import com.lspt.Travels_BE.dto.request.ApiResponse;
import com.lspt.Travels_BE.dto.request.ItineraryCreateRequest;
import com.lspt.Travels_BE.dto.request.ItineraryUpdateRequest;
import com.lspt.Travels_BE.dto.response.ItineraryResponse;
import com.lspt.Travels_BE.service.ItineraryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itineraries")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ItineraryController {
    @Autowired
    private ItineraryService itineraryService;

    @PostMapping
    public ApiResponse<ItineraryResponse> createItinerary(@Valid @RequestBody ItineraryCreateRequest request){
        return ApiResponse.<ItineraryResponse>builder()
                .result(itineraryService.createItinerary(request))
                .build();
    }
  
    @GetMapping
    ApiResponse<List<ItineraryResponse>> getItineraries() {
        return ApiResponse.<List<ItineraryResponse>>builder()
                .result(itineraryService.getItineraries())
    @PutMapping("/{itineraryId}")
    public ApiResponse<ItineraryResponse> updateItinerary(@PathVariable String itineraryId, @RequestBody ItineraryUpdateRequest request){
        return ApiResponse.<ItineraryResponse>builder()
                .result(itineraryService.updateItinerary(itineraryId, request))
                .build();
    }

    @GetMapping("/{itineraryId}")
    ApiResponse<ItineraryResponse> getItinerary(@PathVariable String itineraryId){
        return ApiResponse.<ItineraryResponse>builder()
                .result(itineraryService.getItinerary(itineraryId))
                .build();
    }

    @PutMapping("/{itineraryId}")
    ApiResponse<ItineraryResponse> updateItinerary(@PathVariable String itineraryId, @RequestBody ItineraryUpdateRequest request) {
        return ApiResponse.<ItineraryResponse>builder()
                .result(itineraryService.updateItinerary(itineraryId, request))
                .build();
    }

    @DeleteMapping("/{itineraryId}")
    ApiResponse<String> deleteItinerary(@PathVariable String itineraryId) {
        itineraryService.deleteItinerary(itineraryId);
        return ApiResponse.<String>builder()
                .result("Itinerary has been delete")
                .build();
    }
}
