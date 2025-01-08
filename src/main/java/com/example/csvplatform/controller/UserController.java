package com.example.csvplatform.controller;

import com.example.csvplatform.dtos.UserDto;
import com.example.csvplatform.entities.Organisation;
import com.example.csvplatform.entities.User;
import com.example.csvplatform.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    UserServices userServices;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDto registrationDTO) {
        try {
            userServices.registerUser(registrationDTO);
            return ResponseEntity.ok("Registration successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        try {
            userServices.deleteUser(id);
            return ResponseEntity.ok("Deleted successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deletion failed");
        }
    }

    @GetMapping("/getUsers")
    public  ResponseEntity<?> getUsers () {
        try {
            List<User> users = userServices.getUsers();
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to fetch users");
        }
    }

    @GetMapping("/getOrganiation")
    public ResponseEntity<?> getOrganisations () {
        try {
            List<Organisation> users = userServices.getOrganisation();
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to fetch users");
        }
    }

}

