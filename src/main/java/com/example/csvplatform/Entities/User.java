package com.example.csvplatform.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    private String password;

    @Column(unique = true,nullable = false)
    private String phone;

    private String role;

    private boolean verified;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Volunteer volunteer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Organisation organisation;

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
        if (volunteer != null) {
            volunteer.setUser(this);
        }
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
        if (organisation != null) {
            organisation.setUser(this);
        }
    }


}
