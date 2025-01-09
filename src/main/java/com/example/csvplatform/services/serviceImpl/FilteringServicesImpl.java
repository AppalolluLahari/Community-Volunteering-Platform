package com.example.csvplatform.services.serviceImpl;

import com.example.csvplatform.entities.Task;
import com.example.csvplatform.repositories.TaskRepository;
import com.example.csvplatform.services.FilteringServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilteringServicesImpl implements FilteringServices {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getTasksBySkills(List<String> skills) {
        return taskRepository.findBySkills(skills);
    }


}
