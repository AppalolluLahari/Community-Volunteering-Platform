package com.example.csvplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.csvplatform.entities.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer,Integer> {
}