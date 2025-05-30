package com.lspt.Travels_BE.service;

import com.lspt.Travels_BE.dto.response.TourImageResponse;
import com.lspt.Travels_BE.entity.TourImage;
import com.lspt.Travels_BE.exception.AppException;
import com.lspt.Travels_BE.exception.ErrorCode;
import com.lspt.Travels_BE.mapper.TourImageMapper;
import com.lspt.Travels_BE.repository.TourImageRepository;
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
public class TourImageService {

    TourImageRepository tourImageRepository;
    TourRepository tourRepository;
    TourImageMapper tourImageMapper;
    UploadImageFile uploadImageFile;

    @PreAuthorize("hasRole('ADMIN')")
    public TourImageResponse addImageToExistingTour(String tourId, MultipartFile file) {
        if (!tourRepository.existsById(tourId)) {
            throw new AppException(ErrorCode.TOUR_NOT_EXISTED);
        }

        if (file == null || file.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_FILE);
        }

        try {
            String imageUrl = uploadImageFile.uploadImage(file);

            TourImage image = new TourImage();
            image.setTourId(tourId);
            image.setUrl(imageUrl);

            return tourImageMapper.toTourImageResponse(tourImageRepository.save(image));
        } catch (IOException e) {
            log.error("Error uploading image", e);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    public TourImageResponse updateTourImage(String imageId, String tourId, MultipartFile file) {
        TourImage tourImage = tourImageRepository.findById(imageId)
                .orElseThrow(() -> new AppException(ErrorCode.TOURIMAGE_NOT_EXISTED));

        if (file != null && !file.isEmpty()) {
            try {
                String newUrl = uploadImageFile.uploadImage(file);
                tourImage.setUrl(newUrl);
            } catch (IOException e) {
                log.error("Error uploading image", e);
                throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
            }
        }

        tourImage.setTourId(tourId); // cập nhật lại nếu tourId khác
        return tourImageMapper.toTourImageResponse(tourImageRepository.save(tourImage));
    }

    public List<TourImageResponse> getTourImagesByTourId(String tourId) {
        return tourImageRepository.findByTourId(tourId).stream()
                .map(tourImageMapper::toTourImageResponse)
                .toList();
    }

    public TourImageResponse getTourImage(String imageId) {
        TourImage image = tourImageRepository.findById(imageId)
                .orElseThrow(() -> new AppException(ErrorCode.TOURIMAGE_NOT_EXISTED));
        return tourImageMapper.toTourImageResponse(image);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTourImage(String imageId) {
        if (!tourImageRepository.existsById(imageId)) {
            throw new AppException(ErrorCode.TOURIMAGE_NOT_EXISTED);
        }
        tourImageRepository.deleteById(imageId);
    }
}
