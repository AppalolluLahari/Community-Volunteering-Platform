package com.example.csvplatform.services.serviceImpl;

import com.example.csvplatform.dtos.TaskDto;
import com.example.csvplatform.entities.Organisation;
import com.example.csvplatform.entities.Task;
import com.example.csvplatform.repositories.OrganisationRepository;
import com.example.csvplatform.repositories.TaskRepository;
import com.example.csvplatform.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskServices {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    OrganisationRepository organisationRepository;

    @Override
    public void createTask(TaskDto task) {
        LocalDate currDate = LocalDate.now();
        Organisation organisation = organisationRepository.findById(task.getOrganisationId())
                .orElseThrow(() -> new NullPointerException("Organisation not found"));
        Task newTask = new Task();
        newTask.setOrganisation(organisation);
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setLocation(task.getLocation());
        newTask.setCategory(task.getCategory());
        newTask.setCreatedDate(currDate);
        newTask.setStatus(task.getStatus());
        newTask.setEndDate(LocalDate.from(task.getEndDate()));
        taskRepository.save(newTask);
    }

    public void deleteTask (Integer taskId ) {
        taskRepository.deleteById(taskId);
    }

    public void updateTask(Task task) {
        taskRepository.save(task);
    }
}
