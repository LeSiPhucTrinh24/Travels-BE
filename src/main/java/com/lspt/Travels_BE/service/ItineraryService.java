package com.lspt.Travels_BE.service;

import com.lspt.Travels_BE.dto.request.ItineraryCreateRequest;
import com.lspt.Travels_BE.dto.request.ItineraryUpdateRequest;
import com.lspt.Travels_BE.dto.response.ItineraryResponse;
import com.lspt.Travels_BE.entity.Itinerary;
import com.lspt.Travels_BE.exception.AppException;
import com.lspt.Travels_BE.exception.ErrorCode;
import com.lspt.Travels_BE.mapper.ItineraryMapper;
import com.lspt.Travels_BE.repository.ItineraryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItineraryService {
    ItineraryRepository itineraryRepository;
    ItineraryMapper itineraryMapper;

    public ItineraryResponse createItinerary(ItineraryCreateRequest request){
        Itinerary itinerary = itineraryMapper.toItinerary(request);
        return itineraryMapper.toItineraryResponse(itineraryRepository.save(itinerary));
    }

    public ItineraryResponse updateItinerary(String itineraryId, ItineraryUpdateRequest request){
        Itinerary itinerary = itineraryRepository.findById(itineraryId)
                .orElseThrow(()-> new RuntimeException("Itinerary not found"));

        itineraryMapper.updateItinerary(itinerary, request);

        return itineraryMapper.toItineraryResponse(itineraryRepository.save(itinerary));
    }

    public List<ItineraryResponse> getItinerary(){
        return itineraryRepository.findAll().stream()
                .map(itineraryMapper::toItineraryResponse)
                .toList();
    }

    public ItineraryResponse getItinerary(String itineraryId){
        Itinerary itinerary = itineraryRepository.findById(itineraryId)
                .orElseThrow(() -> new RuntimeException("Itinerary not found"));
        return itineraryMapper.toItineraryResponse(itinerary);
    }

    public void deleteItinerary(String itineraryId){
        if(!itineraryRepository.existsById(itineraryId))
            throw new AppException(ErrorCode.ITINERARY_NOT_EXISTED);
        itineraryRepository.deleteById(itineraryId);
    }
}
