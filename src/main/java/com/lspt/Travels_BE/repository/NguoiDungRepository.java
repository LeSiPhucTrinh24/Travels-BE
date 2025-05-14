package com.lspt.Travels_BE.repository;

import com.lspt.Travels_BE.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, String> {
    boolean existsByHoTen(String hoten);
}
