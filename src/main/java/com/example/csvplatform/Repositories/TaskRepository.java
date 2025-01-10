package com.example.csvplatform.repositories;

import com.example.csvplatform.entities.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.csvplatform.entities.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {

    @Query("SELECT t FROM Task t JOIN t.requiredSkills ts WHERE ts.skillName IN :skills")
    List<Task> findBySkills(@Param("skills") List<String> skills);

    @Query("SELECT t FROM Task t WHERE t.organisation.userId = :orgId")
    List<Task> findByOrganisationId(@Param("orgId") Integer id);

    List<Task> findByLocation(String location);

    List<Task> findByCreatedDate(LocalDate date);
}
