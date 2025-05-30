package com.lspt.Travels_BE.repository;

import com.lspt.Travels_BE.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, String>{
    boolean existsByDestinationName(String DestinationName);
}
