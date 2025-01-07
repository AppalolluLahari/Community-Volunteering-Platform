package com.example.csvplatform.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TaskSignUpDto {

    @NotEmpty(message = "volunteer Id cannot be empty")
    private int volunteerId;

    @NotEmpty(message = "task Id cannot be empty")
    private int taskId;

    @NotEmpty(message = "status Id cannot be empty")
    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDate signedUpDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDate completionDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDate cancellationDate;

    @NotNull
    private boolean remainderSent;
}
