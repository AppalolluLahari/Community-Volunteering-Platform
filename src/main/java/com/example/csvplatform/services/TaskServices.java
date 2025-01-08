package com.example.csvplatform.services;

import com.example.csvplatform.dtos.TaskDto;
import com.example.csvplatform.entities.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface TaskServices {

     void createTask (TaskDto task);
     void deleteTask (Integer task_id);
     void updateTask (Task task);
//     void updateStatus (Integer task_id,String status);
//     List<Task> getTasks ();
//     List<Task> searchTask (Integer task_id);
}
