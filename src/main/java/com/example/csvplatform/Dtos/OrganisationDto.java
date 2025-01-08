package com.example.csvplatform.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganisationDto extends VolunteerDto {

    @NotEmpty(message = "Organisation website cannot be empty")
    private String organisationWebsite;

    @NotEmpty(message = "Organisation location cannot be empty")
    private String organisationLocation;

    private int organisationId;
}