package com.lspt.Travels_BE.mapper;

import com.lspt.Travels_BE.dto.request.NguoiDungCreationRequest;
import com.lspt.Travels_BE.dto.request.NguoiDungUpdateRequest;
import com.lspt.Travels_BE.dto.response.NguoiDungResponse;
import com.lspt.Travels_BE.entity.NguoiDung;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NguoiDungMapper {
    NguoiDung toNguoiDung(NguoiDungCreationRequest request);
    void updateNguoiDung(@MappingTarget NguoiDung nguoiDung, NguoiDungUpdateRequest request);

    NguoiDungResponse toNguoiDungResponse(NguoiDung nguoiDung);
}