package com.lspt.Travels_BE.service;

import com.lspt.Travels_BE.dto.request.TourCreateRequest;
import com.lspt.Travels_BE.dto.request.TourUpdateRequest;
import com.lspt.Travels_BE.dto.response.TourResponse;
import com.lspt.Travels_BE.entity.Tour;
import com.lspt.Travels_BE.exception.AppException;
import com.lspt.Travels_BE.exception.ErrorCode;
import com.lspt.Travels_BE.mapper.TourMapper;
import com.lspt.Travels_BE.repository.TourRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TourService {
    TourRepository tourRepository;
    TourMapper tourMapper;
    UploadImageFile uploadImageFile;

    @PreAuthorize("hasRole('ADMIN')")
    public TourResponse createTour(TourCreateRequest request, MultipartFile file){
        if(tourRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.TOUR_EXISTED);
        }

        Tour tour = tourMapper.toTour(request);

        if(file != null && !file.isEmpty()){
            try{
                String tourImg = uploadImageFile.uploadImage(file);
                tour.setCoverImage(tourImg);
            }catch (IOException e){
                log.error("Error uploading avatar", e);
                throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
            }
        }
        return tourMapper.toTourResponse(tourRepository.save(tour));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public TourResponse updateTour(String tourId, TourUpdateRequest request, MultipartFile file) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("Tour not found"));
        tourMapper.updateTour(tour, request);

        if (file != null && !file.isEmpty()) {
            try {
                String tourImg = uploadImageFile.uploadImage(file);
                tour.setCoverImage(tourImg);
            } catch (IOException e) {
                log.error("Error uploading avatar", e);
                throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
            }

        }
        return tourMapper.toTourResponse(tourRepository.save(tour));
    }

    public List<TourResponse> getTour(){
        log.info("In method get Tour");
        return tourRepository.findAll().stream()
                .map(tourMapper::toTourResponse)
                .toList();
    }

    public TourResponse getTour(String tourId){
        log.info("In method get tour by tourId");
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(()-> new RuntimeException("Tour not found"));
        return tourMapper.toTourResponse(tour);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTour(String tourId){
        if(!tourRepository.existsById(tourId)){
            throw new AppException(ErrorCode.TOUR_NOT_EXISTED);
        }
        tourRepository.deleteById(tourId);
    }
}
