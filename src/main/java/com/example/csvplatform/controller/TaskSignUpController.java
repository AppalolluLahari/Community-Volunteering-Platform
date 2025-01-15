package com.example.csvplatform.controller;

import com.example.csvplatform.dtos.TaskSignUpDto;
import com.example.csvplatform.services.TaskServices;
import com.example.csvplatform.services.TaskSignUpServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/taskSignUp")
public class TaskSignUpController {

    @Autowired
    private TaskServices taskServices;

    @Autowired
    private TaskSignUpServices taskSignUpServices;

    @PostMapping("/signUpTask")
    public ResponseEntity<?> signUpTask(@RequestBody @Valid TaskSignUpDto taskSignUpDto) {
        try {
            taskSignUpServices.createTaskSignUp(taskSignUpDto);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully SignedUp for task");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to signup for task");
        }
    }

    @DeleteMapping("/deleteTaskSignUp/{signUpId}")
    public ResponseEntity<?> deleteTaskSignUp(@PathVariable Integer signUpId) {
        try {
            taskSignUpServices.deleteTaskSignUp(signUpId);
            return ResponseEntity.status(HttpStatus.OK).body("Unsigned from task Successfully");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An Error Occurred");
        }
    }

    @PutMapping("/updateStatus/{signUpId}")
    public ResponseEntity<?> updateStatus(@PathVariable Integer signUpId,@RequestParam String status) {
        try {
            taskSignUpServices.updateTaskSignUpStatus(signUpId,status);
            return ResponseEntity.status(HttpStatus.OK).body("Updated the task status");
        }catch (Exception e) {
//            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An Error Occurred");
        }
    }



}
