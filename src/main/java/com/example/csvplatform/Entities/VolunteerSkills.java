package com.example.csvplatform.Entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "volunteer_skills")
public class VolunteerSkills {

    @Id
    @Column(name = "skill_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int skillId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "skill_name", length = 100)
    private String skillName;

}
