package com.example.csvplatform.services;

import com.example.csvplatform.dtos.TaskDto;
import org.springframework.http.ResponseEntity;

public interface TaskServices {

    public ResponseEntity<?> createTask (TaskDto task);
    public ResponseEntity<?> deleteTask (Integer task_id);
}
