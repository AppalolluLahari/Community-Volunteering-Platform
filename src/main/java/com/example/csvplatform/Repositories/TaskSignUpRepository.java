package com.example.csvplatform.Repositories;

import com.example.csvplatform.Entities.TaskReqSkills;
import com.example.csvplatform.Entities.TaskSignUp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskSignUpRepository extends JpaRepository<TaskSignUp,Integer> {
}
