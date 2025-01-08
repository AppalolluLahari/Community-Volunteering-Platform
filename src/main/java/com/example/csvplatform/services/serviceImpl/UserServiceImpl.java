package com.example.csvplatform.services.serviceImpl;

import com.example.csvplatform.dtos.OrganisationDto;
import com.example.csvplatform.dtos.VolunteerDto;
import com.example.csvplatform.entities.Volunteer;
import com.example.csvplatform.repositories.VolunteerRepository;
import com.example.csvplatform.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    VolunteerRepository volunteerRepository;

    @Override
    public void createVolunteer(VolunteerDto user) {
        Volunteer volunteer = new Volunteer();
        volunteer.setName(user.getName());
        volunteer.setEmail(user.getEmail());
        volunteer.setPhone(user.getPhone());
        volunteer.setPassword(user.getPassword());  // Encode password
        volunteer.setRole(user.getRole());
        volunteer.setVerified(user.isVerified());
        volunteer.setLocation("Unknown Location");  // Set a default or handle as needed
        volunteerRepository.save(volunteer);
    }

    @Override
    public ResponseEntity<?> createOrganisation(OrganisationDto organisation) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteOrganisation(Integer organisation_id) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteVolunteer(Integer volunteer_id) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateOrganisation(VolunteerDto organisation) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateVolunteer(VolunteerDto volunteer) {
        return null;
    }

    @Override
    public ResponseEntity<?> getOrgTasks(Integer organisation_id) {
        return null;
    }
}
