package com.example.csvplatform.services;


import com.example.csvplatform.dtos.OrganisationDto;
import com.example.csvplatform.dtos.VolunteerDto;
import org.springframework.http.ResponseEntity;

public interface UserServices {

     ResponseEntity<?> createVolunteer (VolunteerDto volunteer);
     ResponseEntity<?> createOrganisation (OrganisationDto organisation);
     void deleteVolunteer (Integer volunteer_id);
     void deleteOrganisation (Integer organisation_id);
     ResponseEntity<?> updateVolunteer (VolunteerDto volunteer);
     ResponseEntity<?> updateOrganisation (VolunteerDto organisation);

}
