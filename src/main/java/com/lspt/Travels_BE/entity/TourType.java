package com.lspt.Travels_BE.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "TourType")
public class TourType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tourTypeId")
    private String tourTypeId;

    @Column(name = "tourTypeName")
    private String tourTypeName;
}
