package com.example.csvplatform.services;

import com.example.csvplatform.entities.Task;

import java.util.List;

public interface FilteringServices {
    public List<Task> getTasksBySkills(List<String> skills);
}
