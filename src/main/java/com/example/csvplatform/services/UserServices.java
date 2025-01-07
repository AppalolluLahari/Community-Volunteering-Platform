package com.example.csvplatform.services;


import com.example.csvplatform.dtos.OrganisationDto;
import com.example.csvplatform.dtos.VolunteerDto;
import org.springframework.http.ResponseEntity;

public interface UserServices {

    public ResponseEntity<?> createVolunteer (VolunteerDto volunteer);
    public ResponseEntity<?> createOrganisation (OrganisationDto organisation);
    public void deleteVolunteer (Integer volunteer_id);
    public void deleteOrganisation (Integer organisation_id);
    public ResponseEntity<?> updateVolunteer (VolunteerDto volunteer);
    public ResponseEntity<?> updateOrganisation (VolunteerDto organisation);

}
