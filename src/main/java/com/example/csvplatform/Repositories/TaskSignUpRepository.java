package com.example.csvplatform.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.csvplatform.entities.TaskSignUp;

public interface TaskSignUpRepository extends JpaRepository<TaskSignUp,Integer> {
    Optional<TaskSignUp> findByTaskId(Integer taskId);
    void deleteByTaskId(Integer taskId);
    List<TaskSignUp> findByVolunteerId (Integer id);
}
