package com.example.csvplatform.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "skills_required")
public class TaskReqSkills {
    @Id
    @Column(name = "skill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int skillId;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    @JsonBackReference
    private Task task;

    @Column(name = "skill_name", length = 100)
    private String skillName;
}
