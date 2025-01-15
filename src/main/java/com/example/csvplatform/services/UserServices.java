package com.example.csvplatform.services;


import com.example.csvplatform.dtos.UserDto;
import com.example.csvplatform.dtos.VolunteerDetailsDTO;
import com.example.csvplatform.dtos.VolunteerDto;
import com.example.csvplatform.entities.Organisation;
import com.example.csvplatform.entities.User;
import com.example.csvplatform.entities.Volunteer;
import com.example.csvplatform.entities.VolunteerSkills;

import java.util.List;


public interface UserServices {

     void registerUser (UserDto user);
     void deleteUser (Integer user_id);
     void updateVolunteer (VolunteerDto VolunteerDto);
     List<User> getUsers ();
     List<Organisation> getOrganisation();
     List<VolunteerDetailsDTO> getTop10Volunteers();
     List<VolunteerSkills> getVolunteerSkills(Integer id);
}
