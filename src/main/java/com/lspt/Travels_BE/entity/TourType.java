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
@Table(name = "TourType")
public class TourType {
<<<<<<< HEAD
=======

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
>>>>>>> 514b2896ae055d8306855790cfab99ad5aacc892
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tourTypeId")
    private String tourTypeId;

<<<<<<< HEAD
    @Column(name = "name")
    private String name;
=======
    @Column(name = "tourTypeName")
    private String tourTypeName;

>>>>>>> 514b2896ae055d8306855790cfab99ad5aacc892
}
