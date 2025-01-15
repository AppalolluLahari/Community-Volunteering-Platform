package com.example.csvplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.csvplatform.entities.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingRepository extends JpaRepository<Rating,Integer> {
    @Query("SELECT AVG(r.ratingScore) FROM Rating r WHERE r.ratedTo = :volunteerId")
    Double findAverageRatingByVolunteerId(@Param("volunteerId") int volunteerId);


}
