package com.example.csvplatform.controller.api;


import com.example.csvplatform.dtos.TaskSignUpDto;
import com.example.csvplatform.entities.Task;
import com.example.csvplatform.entities.TaskSignUp;
import com.example.csvplatform.services.TaskServices;
import com.example.csvplatform.services.TaskSignUpServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RestController
@RequestMapping("/taskSignUp")
public class TaskSignUpController {

    @Autowired
    private TaskServices taskServices;

    @Autowired
    private TaskSignUpServices taskSignUpServices;

//    @GetMapping("/signUpTask/{id}/{userId}")
//    public ResponseEntity<?> signUpTask(@PathVariable Integer id,@PathVariable Integer userId) {
//        try {
//            taskSignUpServices.createTaskSignUp(id,userId);
//            return ResponseEntity.status(HttpStatus.OK).body("Successfully SignedUp for task");
//        }catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to signup for task");
//        }
//    }
        @GetMapping("/signUpTask/{id}/{userId}")
        public String signUpTask(Model model, @PathVariable Integer id, @PathVariable Integer userId) {
            try {
                taskSignUpServices.createTaskSignUp(id,userId);
                return "/volunteer/tasks";
            }catch (Exception e) {
                model.addAttribute("Error",e);
                return "/volunteer/tasks";
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

    @GetMapping("/getSignedUpTasks/{userId}")
    public ResponseEntity<?> getSignedUpTasks (@PathVariable Integer userId) {
        try {
            List<TaskSignUp> signedUpTasks  =  taskSignUpServices.getSignedUpTasks(userId);
            List<Task> tasks  =  taskServices.getAllTasks();
            return ResponseEntity.status(HttpStatus.OK).body(signedUpTasks);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to fetch Tasks");
        }
    }



}
