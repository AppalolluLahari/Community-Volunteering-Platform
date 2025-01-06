package com.example.csvplatform.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.csvplatform.Entities.Verification;

public interface VerificationRepository extends JpaRepository<Verification,Integer> {
}
