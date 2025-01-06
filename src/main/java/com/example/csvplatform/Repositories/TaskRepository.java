package com.example.csvplatform.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.csvplatform.Entities.Task;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}
