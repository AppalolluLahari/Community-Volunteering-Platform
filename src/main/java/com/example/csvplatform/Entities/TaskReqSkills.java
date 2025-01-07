package com.example.csvplatform.entities;

import jakarta.persistence.*;
import lombok.*;


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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int skillId;

    @Column(name = "task_id")
    private int taskId;

    @Column(name = "skill_name", length = 100)
    private String skillName;
}
