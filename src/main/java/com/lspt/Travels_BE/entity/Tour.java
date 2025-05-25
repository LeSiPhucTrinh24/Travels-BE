package com.lspt.Travels_BE.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tourId")
    private String tourId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "duration")
    private int duration;

    @Column(name = "departureDate")
    private LocalDate departureDate;

    @Column(name = "departureLocation")
    private String departureLocation;

    @Column(name = "maxPeople")
    private int maxPeople;

    @Column(name = "coverImage")
    private String coverImage;

    @Column(name = "tourTypeId")
    private String tourTypeId;

    @Column(name = "status")
    private boolean status;

    @Column(name = "isFeatured")
    private boolean isFeatured;


}
