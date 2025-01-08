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
//        User user = organisationRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found"));

        userRepository.deleteById(userId);
    }

//    @Override
//    public void updateUser(UserDto user) {
//        Organisation organisation = organisationRepository.findById(organisationId)
//                .orElseThrow(() -> new IllegalArgumentException("Organisation with ID " + organisationId + " not found"));
//
//        // Update the fields
//        organisation.setOrganisationWebsite(website);
//        organisation.setOrganisationLocation(location);
//
//        // Save the updated organisation
//        return organisationRepository.save(organisation);
//
//    }

    @Override
    public void updateUser(UserDto user){

    }
}
