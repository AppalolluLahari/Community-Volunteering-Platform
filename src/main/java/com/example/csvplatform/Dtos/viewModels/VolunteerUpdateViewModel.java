package com.example.csvplatform.dtos.viewModels;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerUpdateViewModel {

    private String userId;

    private String name;

    private String email;

    private String password;

    private String phone;

    private String role;

    private boolean verified;
}
