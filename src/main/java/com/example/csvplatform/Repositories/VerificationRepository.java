package com.example.csvplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.csvplatform.entities.Verification;

public interface VerificationRepository extends JpaRepository<Verification,Integer> {
}
