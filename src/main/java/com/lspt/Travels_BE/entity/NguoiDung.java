package com.lspt.Travels_BE.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private String nguoiDungId;
    private String hoTen;
    private String email;
    private String matKhau;
    private String soDienThoai;
    private String diaChi;
    private String vaiTro;
}
