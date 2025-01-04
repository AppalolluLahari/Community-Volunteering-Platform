package com.example.csvplatform.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private int skillId;

    @Column(name = "task_id")
    private int taskId;

    @Column(name = "skill_name", length = 100)
    private String skillName;
}
