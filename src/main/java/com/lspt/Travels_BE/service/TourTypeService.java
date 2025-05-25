package com.lspt.Travels_BE.service;

<<<<<<< HEAD
import com.lspt.Travels_BE.dto.request.TourTypeCreateRequest;
import com.lspt.Travels_BE.dto.request.TourTypeUpdateRequest;
import com.lspt.Travels_BE.dto.response.TourTypeResponse;
import com.lspt.Travels_BE.entity.Tour;
import com.lspt.Travels_BE.entity.TourType;
import com.lspt.Travels_BE.exception.AppException;
import com.lspt.Travels_BE.exception.ErrorCode;
import com.lspt.Travels_BE.mapper.TourTypeMapper;
import com.lspt.Travels_BE.repository.TourTypeRepository;
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
    TourTypeRepository tourTypeRepository;
    TourTypeMapper tourTypeMapper;

    public TourTypeResponse createTourType(TourTypeCreateRequest request){
        if(tourTypeRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.TOURTYPE_EXISTED);

        TourType tourType = tourTypeMapper.toTourType(request);

        return tourTypeMapper.toTourTypeResponse(tourTypeRepository.save(tourType));
    }

    public TourTypeResponse updateTourType(String tourTypeId, TourTypeUpdateRequest request){
        TourType tourType = tourTypeRepository.findById(tourTypeId)
                .orElseThrow(()-> new RuntimeException("TourType not found"));
        tourTypeMapper.updateTourType(tourType, request);

        return tourTypeMapper.toTourTypeResponse((tourTypeRepository.save(tourType)));
    }

    public List<TourTypeResponse> getTourType(){
        return tourTypeRepository.findAll().stream()
                .map(tourTypeMapper::toTourTypeResponse)
                .toList();
    }

    public TourTypeResponse getTourType(String tourTypeId){
        TourType tourType = tourTypeRepository.findById(tourTypeId)
                .orElseThrow(()-> new RuntimeException("TourType not found"));
        return tourTypeMapper.toTourTypeResponse(tourType);
    }

    public void deleteTourType(String tourTypeId){
        if(!tourTypeRepository.existsById(tourTypeId))
            throw new AppException(ErrorCode.TOURTYPE_NOT_EXISTED);

        tourTypeRepository.deleteById(tourTypeId);
=======

public class TourTypeService {

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
>>>>>>> 514b2896ae055d8306855790cfab99ad5aacc892
    }
}
