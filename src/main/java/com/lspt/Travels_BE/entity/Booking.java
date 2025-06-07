package com.lspt.Travels_BE.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    @Column(name = "departureDate")
    private LocalDate departureDate;

    private LocalDateTime bookingDate;

    private int status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tourId", insertable = false, updatable = false)
    private  Tour tour;
}
