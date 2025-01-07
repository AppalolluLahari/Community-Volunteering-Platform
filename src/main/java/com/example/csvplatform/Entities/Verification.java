package com.example.csvplatform.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
