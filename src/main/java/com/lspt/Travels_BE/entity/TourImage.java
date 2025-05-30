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
@Table(name = "TourImage")
public class TourImage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String imageId;

    private String tourId;

    private String url;

}
