package com.example.csvplatform.repositories;

import com.example.csvplatform.entities.TaskSignUp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskSignUpRepository extends JpaRepository<TaskSignUp,Integer> {
}
