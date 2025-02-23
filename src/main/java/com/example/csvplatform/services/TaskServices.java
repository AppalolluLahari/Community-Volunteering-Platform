package com.example.csvplatform.services;

import com.example.csvplatform.dtos.TaskDto;
import com.example.csvplatform.entities.Task;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface TaskServices {

     void createTask (TaskDto task);
     void deleteTask (Integer task_id);
     void updateTask (Integer task_id,TaskDto taskDto );
     void updateStatus (Integer task_id,String status);
     List<Task> getMyTasks (Integer id);
     @EntityGraph(attributePaths = {"organisation"})
     List<Task> getAllTasks ();
     List<Task> searchTasksByTitle(String name);
//     List<Task> searchTask (Integer task_id);
}
