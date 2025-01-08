package com.example.csvplatform.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "verification")
public class Verification {

    @Id
    @Column(name = "verification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int verificationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "verification_code", length = 5)
    private String verificationCode;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "valid_till", nullable = false)
    private LocalDate validTill;




}
