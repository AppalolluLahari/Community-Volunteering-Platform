package com.example.csvplatform.entities;
<<<<<<< HEAD

import jakarta.persistence.*;
import lombok.*;
=======
>>>>>>> 2edd5c31544680f072f7379e2c12ba4ba52b8664

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int verificationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "verification_code", length = 5)
    private String verificationCode;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "valid_till", nullable = false)
    private LocalDateTime validTill;




}
