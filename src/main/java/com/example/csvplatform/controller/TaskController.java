package com.example.csvplatform.controller;

import com.example.csvplatform.dtos.TaskDto;
import com.example.csvplatform.entities.Task;
import com.example.csvplatform.repositories.TaskRepository;
import com.example.csvplatform.services.TaskServices;
import jakarta.validation.Valid;
import org.hibernate.dialect.SybaseSqmToSqlAstConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskServices taskServices;

    @PostMapping("/createTask")
    public ResponseEntity<?> createTask (@RequestBody @Valid TaskDto taskDto) {
        try {
            taskServices.createTask(taskDto);
            return ResponseEntity.ok("Task created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task Creation failed");
        }
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        try {
            taskServices.deleteTask(id);
            return ResponseEntity.ok("Deleted successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deletion failed");
        }
    }
    @PutMapping("/updateTask/{id}")
    public ResponseEntity<?> updateTask (@PathVariable Integer id,@RequestBody TaskDto task) {
        try {
            taskServices.updateTask(id,task);
            return ResponseEntity.ok("Task updated successfully");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task updation failed");
        }
    }

}
