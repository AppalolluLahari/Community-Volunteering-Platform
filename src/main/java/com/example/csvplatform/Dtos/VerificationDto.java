package com.example.csvplatform.Dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationDto {

    @NotEmpty(message = "Verification ID cannot be empty")
    private int verificationId;

    @NotEmpty(message = "User ID cannot be empty")
    private int userId;

    @NotEmpty(message = "Verification code cannot be empty")
    private String verificationCode;

    private LocalDateTime createdAt;

    private LocalDateTime validTill;
}