package com.lspt.Travels_BE.controller;


import com.lspt.Travels_BE.dto.request.ApiResponse;
import com.lspt.Travels_BE.dto.request.NguoiDungCreationRequest;
import com.lspt.Travels_BE.dto.request.NguoiDungUpdateRequest;
import com.lspt.Travels_BE.dto.response.NguoiDungResponse;
import com.lspt.Travels_BE.entity.NguoiDung;
import com.lspt.Travels_BE.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class NguoiDungController {
    @Autowired
    private NguoiDungService nguoiDungService;

    @PostMapping
    ApiResponse<NguoiDung> createNguoiDung(@RequestBody @Valid NguoiDungCreationRequest request){
        ApiResponse<NguoiDung> apiResponse = new ApiResponse<>();
        apiResponse.setResult(nguoiDungService.createNguoiDung(request));
        return apiResponse;
    }

    @PutMapping("/{userId}")
    NguoiDungResponse updateNguoiDung(@PathVariable String userId, @RequestBody NguoiDungUpdateRequest request){
        return nguoiDungService.updateNguoiDung(userId, request);
    }

    @GetMapping
    List<NguoiDung> getNguoiDung(){
        return nguoiDungService.getNguoiDung();
    }

    @GetMapping("/{userId}")
    NguoiDungResponse getNguoiDung(@PathVariable String userId){
        return nguoiDungService.getNguoiDung(userId);
    }

    @DeleteMapping("/{userId}")
    String deleteNguoiDung(@PathVariable String userId){
        nguoiDungService.deleteNguoiDung(userId);
        return "Đã xóa thành công";
    }
}

