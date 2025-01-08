package com.example.csvplatform.services;


import com.example.csvplatform.dtos.OrganisationDto;
import com.example.csvplatform.dtos.VolunteerDto;
import com.example.csvplatform.entities.Volunteer;
import jakarta.persistence.Entity;
import org.springframework.http.ResponseEntity;


public interface UserServices {

     void createVolunteer (VolunteerDto volunteer);
     ResponseEntity<?> createOrganisation (OrganisationDto organisation);
     ResponseEntity<?> getOrgTasks (Integer organisation_id);
     ResponseEntity<?> deleteVolunteer (Integer volunteer_id);
     ResponseEntity<?> deleteOrganisation (Integer organisation_id);
     ResponseEntity<?> updateVolunteer (VolunteerDto volunteer);
     ResponseEntity<?> updateOrganisation (VolunteerDto organisation);

}
