package com.lspt.Travels_BE.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Itinerary")
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "itineraryId", columnDefinition = "NVARCHAR(255)")
    private String itineraryId;

    @Column(name = "tourId", columnDefinition = "NVARCHAR(255)")
    private String tourId;

    @Column(name = "dayNumber")
    private int dayNumber;

    @Column(name = "dayTitle", columnDefinition = "NVARCHAR(255)")
    private String dayTitle;

    @Column(name = "description", columnDefinition = "NVARCHAR(255)")
    private String description;

    @Column(name = "destinationId", columnDefinition = "NVARCHAR(255)")
    private String destinationId;
}