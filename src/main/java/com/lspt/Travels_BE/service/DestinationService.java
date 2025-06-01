package com.lspt.Travels_BE.service;

import com.lspt.Travels_BE.dto.request.DestinationRequest;
import com.lspt.Travels_BE.dto.response.DestinationResponse;
import com.lspt.Travels_BE.entity.Destination;
import com.lspt.Travels_BE.exception.AppException;
import com.lspt.Travels_BE.exception.ErrorCode;
import com.lspt.Travels_BE.mapper.DestinationMapper;
import com.lspt.Travels_BE.repository.DestinationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class DestinationService {
    DestinationRepository destinationRepository;
    DestinationMapper destinationMapper;

    public DestinationResponse createDestination(DestinationRequest request){
        if(destinationRepository.existsByDestinationName(request.getDestinationName()))
            throw new AppException(ErrorCode.DESTINATIONNAME_EXISTED);

        Destination destination = destinationMapper.toDestination(request);

        return destinationMapper.toDestinationResponse(destinationRepository.save(destination));
    }

    public DestinationResponse updateDestination(String destinationId, DestinationRequest request){
        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(()-> new RuntimeException("Destination not found"));

        destinationMapper.updateDestination(destination, request);

        return destinationMapper.toDestinationResponse(destinationRepository.save(destination));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<DestinationResponse> getDestinations(){
        return destinationRepository.findAll().stream().map(destinationMapper::toDestinationResponse).toList();
    }

    public void deleteDestination(String destinationId){
        destinationRepository.deleteById(destinationId);
    }

    public DestinationResponse getDestination(String tourTypeId){
        return destinationMapper.toDestinationResponse(destinationRepository.findById(tourTypeId)
                .orElseThrow(()-> new RuntimeException("Destination not found")));
    }
}
