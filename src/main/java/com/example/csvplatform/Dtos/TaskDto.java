package com.example.csvplatform.dtos;

import java.time.LocalDate;
import java.util.List;
// import java.time.LocalDateTime;

import com.example.csvplatform.entities.TaskReqSkills;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private int organisationId;

    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotEmpty(message = "Location cannot be empty")
    private String location;

    @NotEmpty(message = "Category cannot be empty")
    private String category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate createdDate;

    @NotEmpty(message = "Status cannot be empty")
    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    private List<String> skills;
}
