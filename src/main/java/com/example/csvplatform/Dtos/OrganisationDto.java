package com.example.csvplatform.Dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganisationDto {

    @NotEmpty(message = "Organisation ID cannot be empty")
    private int organisationId;

    @NotEmpty(message = "Organisation website cannot be empty")
    private String organisationWebsite;

    @NotEmpty(message = "Organisation location cannot be empty")
    private String organisationLocation;
}