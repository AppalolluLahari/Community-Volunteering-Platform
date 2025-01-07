package com.example.csvplatform.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "task_signup")
public class TaskSignUp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "signup_id")
    private int signupId;

    @Column(name = "volunteer_id", nullable = false)
    private int volunteerId;

    @Column(name = "task_id", nullable = false)
    private int taskId;

    private String status;

    @Column(name = "signedup_date")
    private LocalDate signedUpDate;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @Column(name = "cancellation_date")
    private LocalDate cancellationDate;

    @Column(name = "remainder_sent")
    private boolean remainderSent;
}
