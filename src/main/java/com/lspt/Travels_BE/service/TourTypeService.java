package com.lspt.Travels_BE.service;

import com.lspt.Travels_BE.dto.request.TourTypeCreateRequest;
import com.lspt.Travels_BE.dto.request.TourTypeUpdateRequest;
import com.lspt.Travels_BE.dto.response.TourTypeResponse;
import com.lspt.Travels_BE.entity.TourType;
import com.lspt.Travels_BE.exception.AppException;
import com.lspt.Travels_BE.exception.ErrorCode;
import com.lspt.Travels_BE.mapper.TourTypeMapper;
import com.lspt.Travels_BE.repository.TourTypeReponsitory;
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
public class TourTypeService {
    TourTypeReponsitory tourTypeReponsitory;
    TourTypeMapper tourTypeMapper;

    public TourTypeResponse createTourType(TourTypeCreateRequest request){
        if(tourTypeReponsitory.existsByTourTypeName(request.getTourTypeName()))
            throw new AppException(ErrorCode.TOURTYPE_EXISTED);

        TourType tourType = tourTypeMapper.toTourType(request);

        return tourTypeMapper.toTourTypeResponse(tourTypeReponsitory.save(tourType));
    }

    public TourTypeResponse updateTourType(String tourTypeId, TourTypeUpdateRequest request){
        TourType tourType = tourTypeReponsitory.findById(tourTypeId)
                .orElseThrow(()-> new RuntimeException("TourType not found"));
        tourTypeMapper.updateTourType(tourType, request);

        return tourTypeMapper.toTourTypeResponse((tourTypeReponsitory.save(tourType)));
    }

    public List<TourTypeResponse> getTourType(){
        return tourTypeReponsitory.findAll().stream()
                .map(tourTypeMapper::toTourTypeResponse)
                .toList();
    }

    public TourTypeResponse getTourType(String tourTypeId){
        TourType tourType = tourTypeReponsitory.findById(tourTypeId)
                .orElseThrow(()-> new RuntimeException("TourType not found"));
        return tourTypeMapper.toTourTypeResponse(tourType);
    }

    public void deleteTourType(String tourTypeId){
        if(!tourTypeReponsitory.existsById(tourTypeId))
            throw new AppException(ErrorCode.TOURTYPE_NOT_EXISTED);

        tourTypeReponsitory.deleteById(tourTypeId);
}
}
