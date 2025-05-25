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
@Table(name = "Destination")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "destinationId")
    private String destinationId;

    @Column(name = "destinationName")
    private String destinationName;

    @Column(name = "description")
    private String description;
}
