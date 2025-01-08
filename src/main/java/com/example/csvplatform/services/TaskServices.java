package com.example.csvplatform.services;

import com.example.csvplatform.dtos.TaskDto;
import org.springframework.http.ResponseEntity;

public interface TaskServices {

     ResponseEntity<?> createTask (TaskDto task);
     ResponseEntity<?> deleteTask (Integer task_id);
     ResponseEntity<?> updateTask (TaskDto task);
     ResponseEntity<?> updateStatus (Integer task_id,String status);
     ResponseEntity<?> getTasks ();
     ResponseEntity<?> searchTask (Integer task_id);
}
