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
@Table(name = "volunteer_skills")
public class VolunteerSkills {

    @Id
    @Column(name = "skill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int skillId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "volunteer_id",referencedColumnName = "user_id")
    private Volunteer volunteer;

    @Column(name = "skill_name", length = 100)
    private String skillName;

}
