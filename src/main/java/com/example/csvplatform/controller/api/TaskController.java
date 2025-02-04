package com.example.csvplatform.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.csvplatform.dtos.TaskDto;
import com.example.csvplatform.entities.Task;
import com.example.csvplatform.services.TaskServices;

import jakarta.validation.Valid;

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
//            e.printStackTrace();
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



    @GetMapping("/getMyTasks/{organisation_id}")
    public ResponseEntity<?> getAllTasksById (@PathVariable Integer organisation_id) {
        try {
            List<Task> tasks  =  taskServices.getMyTasks(organisation_id);
            return ResponseEntity.status(HttpStatus.OK).body(tasks);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to fetch Tasks");
        }
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity<?> getAllTasks () {
        try {
            List<Task> tasks  =  taskServices.getAllTasks();
            return ResponseEntity.status(HttpStatus.OK).body(tasks);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to fetch Tasks");
        }
    }


    @GetMapping("/getTask/{title}")
    public ResponseEntity<?> getAllTasks (@PathVariable String title) {
        try {
            List<Task> tasks = taskServices.searchTasksByTitle(title);
            return ResponseEntity.status(HttpStatus.OK).body(tasks);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to fetch Tasks");
        }
    }
}
