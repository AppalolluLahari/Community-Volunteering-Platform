package com.example.csvplatform.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.csvplatform.Entities.Organisation;

public interface OrganisationRepository extends JpaRepository<Organisation,Integer> {
}
