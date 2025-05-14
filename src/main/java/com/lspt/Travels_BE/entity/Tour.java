package com.lspt.Travels_BE.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Tour")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tour_id")
    private String tourId;

    @Column(name = "ten_tour")
    private String tenTour;

    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String moTa;

    @Column(name = "gia")
    private BigDecimal gia;

    @Column(name = "thoi_gian")
    private String thoiGian;

    @Column(name = "ngay_khoi_hanh")
    private LocalDate ngayKhoiHanh;

    @Column(name = "diem_khoi_hanh")
    private String diemKhoiHanh;

    @Column(name = "so_nguoi_toi_da")
    private Integer soNguoiToiDa;

    @Column(name = "anh_dai_dien")
    private String anhDaiDien;

    @Column(name = "loai_tour_id")
    private String loaiTourId; // có thể thay bằng @ManyToOne nếu có entity LoaiTour
}
