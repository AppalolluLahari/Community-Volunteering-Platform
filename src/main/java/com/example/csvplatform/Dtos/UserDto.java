package com.example.csvplatform.Dtos;

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
public class UserDto {

    @NotEmpty(message = "Name Cannot be Empty")
    private String name;

    @Email(message = "Email Cannot be Empty")
    private String email;

    @NotEmpty(message = "Password Cannot be Empty")
    private String password;

    @NotEmpty(message = "Phone Number Cannot be Empty")
    private String phone;

    private String role;

    @NotEmpty(message = "Verification Status should be mentioned")
    private boolean verified;
}
