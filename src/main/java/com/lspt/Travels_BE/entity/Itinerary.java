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
    private String itineraryId;
    private String tourId;
    private String dayTitle;
    private int dayNumber;
    private String description;
    private String destinationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destinationId", insertable = false, updatable = false)
    private Destination destination;
}
