package com.lspt.Travels_BE.service;

import com.lspt.Travels_BE.dto.request.ItineraryCreateRequest;
import com.lspt.Travels_BE.dto.request.ItineraryUpdateRequest;
import com.lspt.Travels_BE.dto.response.ItineraryResponse;
import com.lspt.Travels_BE.entity.Itinerary;
import com.lspt.Travels_BE.exception.AppException;
import com.lspt.Travels_BE.exception.ErrorCode;
import com.lspt.Travels_BE.mapper.ItineraryMapper;
import com.lspt.Travels_BE.repository.DestinationRepository;
import com.lspt.Travels_BE.repository.ItineraryRepository;
import com.lspt.Travels_BE.repository.TourRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ItineraryService {
    ItineraryRepository itineraryRepository;

    ItineraryMapper itineraryMapper;

    DestinationRepository destinationRepository;

    TourRepository tourRepository;

    public ItineraryResponse createItinerary(ItineraryCreateRequest request){
        if(!tourRepository.existsById(request.getTourId()))
            throw new AppException(ErrorCode.TOUR_NOT_EXISTED);

        if(!destinationRepository.existsById(request.getDestinationId()))
            throw new AppException(ErrorCode.DESTINATION_NOT_EXISTED);

        List<Itinerary> existingItineraries = itineraryRepository.findAllByTourIdAndDestinationId(
                request.getTourId(), request.getDestinationId());

        // Lấy danh sách các dayNumber hiện có
        List<Integer> existingDayNumbers = existingItineraries.stream()
                .map(Itinerary::getDayNumber)
                .collect(Collectors.toList());

        // Kiểm tra xem dayNumber mới có trùng với các dayNumber hiện có hay không
        if (existingDayNumbers.contains(request.getDayNumber())) {
            throw new IllegalArgumentException("Số ngày " + request.getDayNumber() +
                    " đã tồn tại cho tourId " + request.getTourId() +
                    " và destinationId " + request.getDestinationId() +
                    ". Các ngày hiện có: " + String.join(", ", existingDayNumbers.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList())));
        }

        Itinerary itinerary = itineraryMapper.toItinerary(request);

        return itineraryMapper.toItineraryResponse(itineraryRepository.save(itinerary));
    }

    public ItineraryResponse updateItinerary(String itineraryId, ItineraryUpdateRequest request){
        Itinerary itinerary = itineraryRepository.findById(itineraryId)
                .orElseThrow(()-> new RuntimeException("Itinerary not found"));

        List<Itinerary> existingItineraries = itineraryRepository.findAllByTourIdAndDestinationId(
                itinerary.getTourId(), itinerary.getDestinationId());

        // Lấy danh sách các dayNumber hiện có loại trừ dayNumber đang được cập nhật
        List<Integer> existingDayNumbers = existingItineraries.stream()
                .filter(i -> !i.getItineraryId().equals(itineraryId)) // Loại bỏ itinerary đang được cập nhật
                .map(Itinerary::getDayNumber)
                .collect(Collectors.toList());

        // Kiểm tra xem dayNumber mới có trùng với các dayNumber hiện có hay không
        if (existingDayNumbers.contains(request.getDayNumber())) {
            throw new IllegalArgumentException("Số ngày " + request.getDayNumber() +
                    " đã tồn tại cho tourId " + itinerary.getTourId() +
                    " và destinationId " + itinerary.getDestinationId() +
                    ". Các ngày hiện có: " + String.join(", ", existingDayNumbers.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList())));
        }

        itineraryMapper.updateItinerary(itinerary, request);

        return itineraryMapper.toItineraryResponse(itineraryRepository.save(itinerary));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<ItineraryResponse> getItineraries(){
        return itineraryRepository.findAllByOrderByTourIdAscDestinationIdAscDayNumberAsc().stream()
                .map(itineraryMapper::toItineraryResponse)
                .toList();
    }

    public void deleteItinerary(String itineraryId){
        itineraryRepository.deleteById(itineraryId);
    }

    public ItineraryResponse getItinerary(String itineraryId){
        return itineraryMapper.toItineraryResponse(itineraryRepository.findById(itineraryId)
                .orElseThrow(()-> new RuntimeException("Itinerary not found")));
    }
}