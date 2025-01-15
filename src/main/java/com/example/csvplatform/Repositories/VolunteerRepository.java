package com.example.csvplatform.repositories;

import com.example.csvplatform.dtos.VolunteerDetailsDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.csvplatform.entities.Volunteer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer,Integer> {
//    @Query(value = "SELECT * FROM volunteer v JOIN user u on v.user_id = u.user_id ORDER BY rating_score DESC LIMIT 10", nativeQuery = true)

//    @Query(value = "SELECT v.location, v.rating_score, u.user_id, u.name, u.email, GROUP_CONCAT(vs.skill_name) AS skills FROM volunteer v JOIN user u ON v.user_id = u.user_id LEFT JOIN volunteer_skills vs ON vs.volunteer_id ", nativeQuery = true)
//    List<Volunteer> findTop10ByRating(Pageable pageable);
//@Query("SELECT v FROM Volunteer v ORDER BY v.ratingScore DESC")
//    List<Volunteer> findTop10ByRating(Pageable pageable);
        @Query("SELECT new com.example.csvplatform.dtos.VolunteerDetailsDTO(" +
                "v.id, v.name, v.email, v.phone, v.role, v.location, v.ratingScore) " +
                "FROM Volunteer v " +
                "ORDER BY v.ratingScore DESC")
        List<VolunteerDetailsDTO> findTop10ByRating(Pageable pageable);

}