package com.example.csvplatform.repositories;

import com.example.csvplatform.entities.TaskSignUp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskSignUpRepository extends JpaRepository<TaskSignUp,Integer> {
    Optional<TaskSignUp> findByTaskId(Integer taskId);
    void deleteByTaskId(Integer taskId);
}
