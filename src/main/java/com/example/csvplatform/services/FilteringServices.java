package com.example.csvplatform.services;

import com.example.csvplatform.entities.Task;

import java.time.LocalDate;
import java.util.List;

public interface FilteringServices {
    List<Task> getTasksBySkills(List<String> skills);
    List<Task> getTaskByLocation(String locaction);
    List<Task> getTaskByDate(LocalDate date);
}
