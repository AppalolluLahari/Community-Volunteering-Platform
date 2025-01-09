package com.example.csvplatform.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.csvplatform.dtos.TaskSignUpDto;
import com.example.csvplatform.entities.Task;
import com.example.csvplatform.entities.TaskSignUp;
import com.example.csvplatform.repositories.TaskRepository;
import com.example.csvplatform.repositories.TaskSignUpRepository;
import com.example.csvplatform.services.TaskSignUpServices;

@Service
public class TaskSignUpServiceImpl implements TaskSignUpServices {

    @Autowired
    private TaskSignUpRepository taskSignupRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void createTaskSignUp(TaskSignUpDto taskSignUpDto) {
        // Verify if the task exists
        Task task = taskRepository.findById(taskSignUpDto.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + taskSignUpDto.getTaskId()));

        // Map DTO to Entity
        TaskSignUp taskSignUp = new TaskSignUp();
        taskSignUp.setVolunteerId(taskSignUpDto.getVolunteerId());
        taskSignUp.setTaskId(task.getTaskId());
        taskSignUp.setSignedUpDate(taskSignUpDto.getSignedUpDate());
        taskSignUp.setCompletionDate(taskSignUpDto.getCompletionDate());
        taskSignUp.setCancellationDate(taskSignUpDto.getCancellationDate());
        taskSignUp.setStatus(taskSignUpDto.getStatus());
        taskSignUp.setRemainderSent(taskSignUpDto.getRemainderSent());

        // Save the task signup
        taskSignupRepository.save(taskSignUp);
    }

    @Override
    public void deleteTaskSignUp(Integer signUpId) {
        if (!taskSignupRepository.existsById(signUpId)) {
            throw new RuntimeException("Task Signup not found with ID: " + signUpId);
        }
        taskSignupRepository.deleteById(signUpId);
    }

    @Override
    public void updateTaskSignUp(TaskSignUpDto taskSignUpDto) {
        TaskSignUp existingSignUp = taskSignupRepository.findById(taskSignUpDto.getSignUpId())
                .orElseThrow(() -> new RuntimeException("Task Signup not found with ID: " + taskSignUpDto.getSignUpId()));

        // Update fields only if they are provided in the DTO
        
            existingSignUp.setVolunteerId(taskSignUpDto.getVolunteerId());
            existingSignUp.setTaskId(taskSignUpDto.getTaskId());
            existingSignUp.setSignedUpDate(taskSignUpDto.getSignedUpDate());
            existingSignUp.setStatus(taskSignUpDto.getStatus());
            existingSignUp.setCompletionDate(taskSignUpDto.getCompletionDate());
            existingSignUp.setCancellationDate(taskSignUpDto.getCancellationDate());
            existingSignUp.setRemainderSent(taskSignUpDto.getRemainderSent());

        taskSignupRepository.save(existingSignUp);
    }
}