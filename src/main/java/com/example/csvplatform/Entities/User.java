package com.example.csvplatform.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int userId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private String phone;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private boolean verified;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Volunteer volunteer;
//
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Organisation organisation;



//    public void setVolunteer(Volunteer volunteer) {
//        this.volunteer = volunteer;
//        if (volunteer != null) {
//            volunteer.setUser(this);
//        }
//    }
//
//    public void setOrganisation(Organisation organisation) {
//        this.organisation = organisation;
//        if (organisation != null) {
//            organisation.setUser(this);
//        }
//    }


}
