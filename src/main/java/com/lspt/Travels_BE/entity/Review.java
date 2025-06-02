package com.lspt.Travels_BE.entity;

import com.lspt.Travels_BE.enums.ReviewStatus;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "reviewId")
    private String reviewId;

    @Column(name = "userId")
    private String userId;

    @Column(name = "tourId")
    private String tourId;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "content", columnDefinition = "NVARCHAR(255)")
    private String content;

    @Column(name = "reviewDate")
    private LocalDateTime reviewDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReviewStatus status;
}
