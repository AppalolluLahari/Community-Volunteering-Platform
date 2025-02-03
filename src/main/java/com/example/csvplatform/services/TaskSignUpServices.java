package com.example.csvplatform.services;

import com.example.csvplatform.dtos.TaskSignUpDto;

public interface TaskSignUpServices {
    void createTaskSignUp(Integer id,Integer userId);

    void deleteTaskSignUp(Integer signUpId);

    void updateTaskSignUp(TaskSignUpDto taskSignUpDto);

    void updateTaskSignUpStatus (Integer signIpId, String status);
}