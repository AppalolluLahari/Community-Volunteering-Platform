package com.example.csvplatform.services.serviceImpl;

import com.example.csvplatform.entities.Volunteer;
import com.example.csvplatform.repositories.VolunteerRepository;
import com.example.csvplatform.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.csvplatform.dtos.TaskSignUpDto;
import com.example.csvplatform.entities.Task;
import com.example.csvplatform.entities.TaskSignUp;
import com.example.csvplatform.repositories.TaskRepository;
import com.example.csvplatform.repositories.TaskSignUpRepository;
import com.example.csvplatform.services.TaskSignUpServices;

import java.time.LocalDate;

@Service
public class TaskSignUpServiceImpl implements TaskSignUpServices {

    @Autowired
    private TaskSignUpRepository taskSignupRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskServices taskServices;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Override
    public void createTaskSignUp(TaskSignUpDto taskSignUpDto) {
        // Verify if the task exists
        Task task = taskRepository.findById(taskSignUpDto.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + taskSignUpDto.getTaskId()));

        Volunteer volunteer = volunteerRepository.findById(taskSignUpDto.getVolunteerId())
                .orElseThrow(() -> new RuntimeException("Volunteer not found with ID: " + taskSignUpDto.getVolunteerId()));

        //Setting the task status to Taken
        taskServices.updateStatus(task.getTaskId(), "Taken");

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
        //Checking for the if it exists
        if (!taskSignupRepository.existsById(signUpId)) {
            throw new RuntimeException("Task Signup not found with ID: " + signUpId);
        }

        TaskSignUp taskSignUp = taskSignupRepository.findById(signUpId)
                .orElseThrow(() -> new RuntimeException("Task SignUp not found with ID: " + signUpId));

        Task task = taskRepository.findById(taskSignUp.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + taskSignUp.getTaskId()));


        LocalDate cancellationDate = taskSignUp.getCancellationDate();
        LocalDate currDate = LocalDate.now();
        if (currDate.isBefore(cancellationDate) || cancellationDate.isEqual(currDate)) {
            //Deleting the task registration
            taskSignupRepository.deleteById(signUpId);
            task.setStatus("Pending");
        }
        else {
            throw new RuntimeException("Deadline crossed task cannot be Cancelled");
        }

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

    @Override
    public void updateTaskSignUpStatus(Integer signIpId, String status) {
        TaskSignUp signUpDetails = taskSignupRepository.findById(signIpId)
                .orElseThrow(() -> new RuntimeException("Task Registration not found"));

        if(status.equalsIgnoreCase("completed")) {
            signUpDetails.setCompletionDate(LocalDate.now());
        }
        signUpDetails.setStatus(status);
        taskSignupRepository.save(signUpDetails);
    }
}