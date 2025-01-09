package com.example.csvplatform.services;

import com.example.csvplatform.dtos.TaskSignUpDto;

public interface TaskSignUpServices {
    void createTaskSignUp(TaskSignUpDto taskSignUpDto);

    void deleteTaskSignUp(Integer signUp_id);

    void updateTaskSignUp(TaskSignUpDto taskSignUpDto);
}