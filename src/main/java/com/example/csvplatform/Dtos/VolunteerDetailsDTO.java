package com.example.csvplatform.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerDetailsDTO {

    private int userId;

    @NotEmpty(message = "Name Cannot be Empty")
    private String name;

    @Email(message = "Email Cannot be Empty")
    private String email;

    @NotEmpty(message = "Phone Number Cannot be Empty")
    private String phone;

    private String role;

    @NotEmpty(message = "Location cannot be empty")
    private String location;

    private Double ratingScore;
}
