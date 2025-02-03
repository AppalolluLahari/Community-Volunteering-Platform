package com.example.csvplatform.controller.api;

import com.example.csvplatform.dtos.UserDto;
import com.example.csvplatform.dtos.VolunteerDetailsDTO;
import com.example.csvplatform.dtos.VolunteerDto;
import com.example.csvplatform.entities.Organisation;
import com.example.csvplatform.entities.User;
import com.example.csvplatform.entities.VolunteerSkills;
import com.example.csvplatform.services.UserServices;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
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

    @GetMapping("/getVolunteers/top10")
    public ResponseEntity<?> getTop10Volunteers () {
        try {
            List<VolunteerDetailsDTO> volunteers = userServices.getTop10Volunteers();
            return ResponseEntity.status(HttpStatus.OK).body(volunteers);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to fetch volunteers");
        }
    }

    @GetMapping("/getVolunteers/skills/{volunteer_id}")
    public ResponseEntity<?> getVolunteerSkills (Integer volunteer_id) {
        try {
            List<VolunteerSkills> skills = userServices.getVolunteerSkills(volunteer_id);
            return ResponseEntity.status(HttpStatus.OK).body(skills);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to fetch volunteers");
        }
    }

    @PostMapping("/updateVolunteer")
    public ResponseEntity<?> updateTask (@RequestBody @Valid VolunteerDto volunteerDto, HttpSession session) {
        try {
            userServices.updateVolunteer(volunteerDto,session);

            return ResponseEntity.ok("updated your details successfully");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("updation failed");
        }
    }
}

