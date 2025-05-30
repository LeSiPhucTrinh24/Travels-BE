package com.lspt.Travels_BE.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String bookingId;

    private String userId;

    private String tourId;

    private int numberOfPeople;

    private double totalPrice;

    private LocalDateTime bookingDate;

    private  boolean status;
}
