package com.example.csvplatform.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
