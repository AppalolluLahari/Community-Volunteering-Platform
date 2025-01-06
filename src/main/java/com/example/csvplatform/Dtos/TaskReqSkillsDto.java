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
public class TaskReqSkillsDto {

    @NotEmpty(message = "Skill ID cannot be empty")
    private int skillId;

    @NotEmpty(message = "Task ID cannot be empty")
    private int taskId;

    @NotEmpty(message = "Skill name cannot be empty")
    private String skillName;
}