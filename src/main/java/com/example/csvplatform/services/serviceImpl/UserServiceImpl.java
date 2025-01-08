package com.example.csvplatform.services.serviceImpl;

import com.example.csvplatform.dtos.OrganisationDto;
import com.example.csvplatform.dtos.UserDto;
import com.example.csvplatform.dtos.VolunteerDto;
import com.example.csvplatform.entities.Organisation;
import com.example.csvplatform.entities.Task;
import com.example.csvplatform.entities.User;
import com.example.csvplatform.entities.Volunteer;
import com.example.csvplatform.repositories.OrganisationRepository;
import com.example.csvplatform.repositories.UserRepository;
import com.example.csvplatform.repositories.VolunteerRepository;
import com.example.csvplatform.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    VolunteerRepository volunteerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrganisationRepository organisationRepository;

    @Override
    public void registerUser(UserDto user) {
        if(("Volunteer").equalsIgnoreCase(user.getRole())) {
            Volunteer volunteer = new Volunteer();
            volunteer.setName(user.getName());
            volunteer.setEmail(user.getEmail());
            volunteer.setPhone(user.getPhone());
            volunteer.setPassword(user.getPassword());
            volunteer.setRole(user.getRole());
            volunteer.setVerified(user.isVerified());
            volunteerRepository.save(volunteer);
        }
        else {
            Organisation organisation = new Organisation();
            organisation.setName(user.getName());
            organisation.setEmail(user.getEmail());
            organisation.setPhone(user.getPhone());
            organisation.setPassword(user.getPassword());
            organisation.setRole(user.getRole());
            organisation.setVerified(user.isVerified());
            organisationRepository.save(organisation);
        }
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getUsers () {
        return userRepository.findAll();
    }

    @Override
    public List<Organisation> getOrganisation() {
        return organisationRepository.findAll();
    }

    @Override
    public void updateUser(UserDto user){

    }
}
