package com.lspt.Travels_BE.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invalidated_token")
public class InvalidatedToken {
    @Id
    private String id;
    private Date expiryTime; // thời gian hết hạn Token
}