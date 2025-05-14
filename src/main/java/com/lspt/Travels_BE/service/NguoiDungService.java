package com.lspt.Travels_BE.service;

import com.lspt.Travels_BE.dto.request.NguoiDungCreationRequest;
import com.lspt.Travels_BE.dto.request.NguoiDungUpdateRequest;
import com.lspt.Travels_BE.dto.response.NguoiDungResponse;
import com.lspt.Travels_BE.entity.NguoiDung;
import com.lspt.Travels_BE.exception.AppException;
import com.lspt.Travels_BE.exception.ErrorCode;
import com.lspt.Travels_BE.repository.NguoiDungRepository;
import com.lspt.Travels_BE.mapper.NguoiDungMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NguoiDungService {
    NguoiDungRepository nguoiDungReponsitory;
    NguoiDungMapper nguoiDungMapper;

    public NguoiDung createNguoiDung(NguoiDungCreationRequest request){

        if(nguoiDungReponsitory.existsByHoTen(request.getHoTen()))
            throw new AppException(ErrorCode.USER_EXISTED);

        NguoiDung nguoiDung = nguoiDungMapper.toNguoiDung(request);

        return nguoiDungReponsitory.save(nguoiDung);
    }
    public List<NguoiDung> getNguoiDung(){
        return nguoiDungReponsitory.findAll();
    }

    public NguoiDungResponse getNguoiDung(String id){
        return nguoiDungMapper.toNguoiDungResponse(nguoiDungReponsitory.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy")));
    }

    public NguoiDungResponse updateNguoiDung(String userId, NguoiDungUpdateRequest request){
        NguoiDung nguoiDung = nguoiDungReponsitory.findById(userId).orElseThrow(() -> new RuntimeException("Không tìm thấy"));

        nguoiDungMapper.updateNguoiDung(nguoiDung, request);

        return nguoiDungMapper.toNguoiDungResponse(nguoiDungReponsitory.save(nguoiDung));
    }

    public void deleteNguoiDung(String userId){
        nguoiDungReponsitory.deleteById(userId);
    }
}
