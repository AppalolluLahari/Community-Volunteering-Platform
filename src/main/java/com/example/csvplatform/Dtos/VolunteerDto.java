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
public class VolunteerDto {

    @NotEmpty(message = "Volunteer ID cannot be empty")
    private int volunteerId;

    @NotEmpty(message = "Location cannot be empty")
    private String location;
}
