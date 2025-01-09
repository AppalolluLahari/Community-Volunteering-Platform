package com.example.csvplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.csvplatform.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating,Integer> {
}
