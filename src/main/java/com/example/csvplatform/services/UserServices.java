package com.example.csvplatform.services;


import com.example.csvplatform.dtos.UserDto;
import com.example.csvplatform.entities.Organisation;
import com.example.csvplatform.entities.User;

import java.util.List;


public interface UserServices {

     void registerUser (UserDto user);
     void deleteUser (Integer user_id);
     void updateUser (UserDto user);
     List<User> getUsers ();
     List<Organisation> getOrganisation();

}
