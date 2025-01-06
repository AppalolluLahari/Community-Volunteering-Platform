package com.example.csvplatform.DTO;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TaskSignUpDto {

    private int volunteerId;

    private int taskId;

    private String status;

    private LocalDate signedUpDate;

    private LocalDate completionDate;

    private LocalDate cancellationDate;

    private boolean remainderSent;
}
