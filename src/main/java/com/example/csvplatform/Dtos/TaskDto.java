package com.example.csvplatform.dtos;

import java.time.LocalDateTime;

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

    @NotEmpty(message = "Task ID cannot be empty")
    private int taskId;

    @NotEmpty(message = "Organisation ID cannot be empty")
    private int organisationId;

    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotEmpty(message = "Location cannot be empty")
    private String location;

    @NotEmpty(message = "Category cannot be empty")
    private String category;

    private LocalDateTime createdDate;

    @NotEmpty(message = "Status cannot be empty")
    private String status;

    private LocalDateTime endDate;
}
