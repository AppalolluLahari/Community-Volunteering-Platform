package com.example.csvplatform.repositories;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.csvplatform.entities.VolunteerSkills;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VolunteerSkillsRepository extends JpaRepository<VolunteerSkills,Integer> {
    @Query("SELECT vs FROM VolunteerSkills vs where vs.volunteer.userId = :volunteerId ")
    List<VolunteerSkills> findByUserId (@Param("volunteerId") Integer volunteerId);

    void deleteByVolunteer_UserId(Integer id);
}
