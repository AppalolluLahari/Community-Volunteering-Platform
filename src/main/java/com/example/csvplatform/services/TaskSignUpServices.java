package com.example.csvplatform.services;

import java.util.List;

import com.example.csvplatform.dtos.TaskSignUpDto;
import com.example.csvplatform.entities.TaskSignUp;

public interface TaskSignUpServices {
    void createTaskSignUp(Integer id,Integer userId);

    void deleteTaskSignUp(Integer signUpId);

    void updateTaskSignUp(TaskSignUpDto taskSignUpDto);

    void updateTaskSignUpStatus (Integer signIpId, String status);

    List<TaskSignUp> getSignedUpTasks (Integer id);
}