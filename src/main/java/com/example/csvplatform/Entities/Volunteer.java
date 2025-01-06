package com.example.csvplatform.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "volunteer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Volunteer {

    @Id
    @Column(name = "volunteer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int volunteerId;

    @Column(name = "location", length = 100)
    private String location;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "volunteer", cascade = CascadeType.ALL)
    private List<VolunteerSkills> volunteerSkills;

}
