package com.example.csvplatform.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "task")
public class Task {

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int taskId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    private String title;

    private String description;

    private String location;

    private String category;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    private String status;

    @Column(name = "end_date")
    private LocalDateTime endDate;

}