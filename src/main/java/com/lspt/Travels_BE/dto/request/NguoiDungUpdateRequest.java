package com.lspt.Travels_BE.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NguoiDungUpdateRequest {

    @Size(min = 5, message = "USERNAME_INVALID")
    private String hoTen;
    private String matKhau;
    private String soDienThoai;
    private String diaChi;
    private String vaiTro;
}
