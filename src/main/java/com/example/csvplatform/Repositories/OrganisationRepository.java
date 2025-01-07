package com.example.csvplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.csvplatform.entities.Organisation;

public interface OrganisationRepository extends JpaRepository<Organisation,Integer> {
}

