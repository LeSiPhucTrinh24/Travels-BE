package com.lspt.Travels_BE.service;

import com.lspt.Travels_BE.dto.request.TourTypeRequest;
import com.lspt.Travels_BE.dto.request.UserUpdateRequest;
import com.lspt.Travels_BE.dto.response.TourTypeResponse;
import com.lspt.Travels_BE.dto.response.UserResponse;
import com.lspt.Travels_BE.entity.TourType;
import com.lspt.Travels_BE.entity.User;
import com.lspt.Travels_BE.exception.AppException;
import com.lspt.Travels_BE.exception.ErrorCode;
import com.lspt.Travels_BE.mapper.TourTypeMapper;
import com.lspt.Travels_BE.repository.TourTypeReponsitory;
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
public class TourTypeService {
    TourTypeReponsitory tourTypeReponsitory;
    TourTypeMapper tourTypeMapper;

    public TourTypeResponse createTourType(TourTypeRequest request){
        if(tourTypeReponsitory.existsByTourTypeName(request.getTourTypeName()))
            throw new AppException(ErrorCode.NAMETOURTYPE_EXISTED);

        TourType tourType = tourTypeMapper.toTourType(request);

        return tourTypeMapper.toTourTypeResponse(tourTypeReponsitory.save(tourType));
    }

    public TourTypeResponse updateTourType(String tourTypeId, TourTypeRequest request){
        TourType tourType = tourTypeReponsitory.findById(tourTypeId)
                .orElseThrow(()-> new RuntimeException("Tour type not found"));

        tourTypeMapper.updateTourType(tourType, request);

        return tourTypeMapper.toTourTypeResponse(tourTypeReponsitory.save(tourType));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<TourTypeResponse> getTourType(){
        return tourTypeReponsitory.findAll().stream().map(tourTypeMapper::toTourTypeResponse).toList();
    }

    public void deleteTourType(String tourTypeId){
        tourTypeReponsitory.deleteById(tourTypeId);
    }

    public TourTypeResponse getTourType(String tourTypeId){
        return tourTypeMapper.toTourTypeResponse(tourTypeReponsitory.findById(tourTypeId)
                .orElseThrow(()-> new RuntimeException("Tour type not found")));
    }
}
