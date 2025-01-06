package com.example.csvplatform.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.csvplatform.Entities.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer,Integer> {
}