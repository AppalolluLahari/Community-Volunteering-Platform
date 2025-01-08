package com.example.csvplatform.services;


import com.example.csvplatform.dtos.OrganisationDto;
import com.example.csvplatform.dtos.UserDto;
import com.example.csvplatform.dtos.VolunteerDto;
import com.example.csvplatform.entities.Task;
import com.example.csvplatform.entities.User;
import com.example.csvplatform.entities.Volunteer;
import jakarta.persistence.Entity;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserServices {

     void registerUser (UserDto user);
     void deleteUser (Integer user_id);
     void updateUser (UserDto user);

}
