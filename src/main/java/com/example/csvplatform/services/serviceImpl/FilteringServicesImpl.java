package com.example.csvplatform.services.serviceImpl;

import com.example.csvplatform.entities.Task;
import com.example.csvplatform.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FilteringServicesImpl {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasksBySkills(List<String> skills) {
        return taskRepository.findBySkills(skills);
    }


}
