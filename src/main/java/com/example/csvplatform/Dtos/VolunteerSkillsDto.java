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
public class VolunteerSkillsDto {

    @NotEmpty(message = "Skill ID cannot be empty")
    private int skillId;

    @NotEmpty(message = "User ID cannot be empty")
    private int userId;

    @NotEmpty(message = "Skill name cannot be empty")
    private String skillName;
}