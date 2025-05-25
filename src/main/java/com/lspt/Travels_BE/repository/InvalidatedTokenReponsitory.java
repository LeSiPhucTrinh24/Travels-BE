package com.lspt.Travels_BE.repository;

import com.lspt.Travels_BE.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedTokenReponsitory extends JpaRepository<InvalidatedToken, String> {
}
