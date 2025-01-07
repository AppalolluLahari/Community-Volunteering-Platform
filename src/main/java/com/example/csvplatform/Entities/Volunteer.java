package com.example.csvplatform.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "volunteer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Volunteer extends User{


    private int volunteerId = super.userId;

    @Column(name = "location", length = 100)
    private String location;

    @OneToMany(mappedBy = "volunteer", cascade = CascadeType.ALL)
    private List<VolunteerSkills> volunteerSkills;

}
