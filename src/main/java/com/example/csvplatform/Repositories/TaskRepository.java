package com.example.csvplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.csvplatform.entities.Task;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}
