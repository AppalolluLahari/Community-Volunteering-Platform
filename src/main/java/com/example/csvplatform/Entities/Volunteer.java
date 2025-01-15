package com.example.csvplatform.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
// import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "volunteer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Volunteer extends User{


//    @Column(unique = true)
//    @GeneratedValue (strategy = GenerationType.IDENTITY)
//    private int volunteerId = super.userId;

    @Column(name = "location", length = 100)
    private String location;

    @OneToMany(mappedBy = "volunteer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<VolunteerSkills> volunteerSkills;

    @Column(name = "rating_score")
    private Double ratingScore;
}
