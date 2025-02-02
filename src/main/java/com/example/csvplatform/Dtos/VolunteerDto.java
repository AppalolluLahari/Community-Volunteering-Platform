package com.example.csvplatform.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerDto {

    private int userId;

    @NotEmpty(message = "Name Cannot be Empty")
    private String name;

    @NotEmpty(message = "Phone Number Cannot be Empty")
    private String phone;

    private String location;

    private int ratingScore;

    private List<String> volunteerSkills;
}
