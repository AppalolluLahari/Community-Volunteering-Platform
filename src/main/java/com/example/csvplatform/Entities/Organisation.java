package com.example.csvplatform.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "organisation")
public class Organisation extends User {


    @Column(name = "organisation_website", length = 50)
    private String organisationWebsite;

    @Column(name = "organisation_location", length = 100)
    private String organisationLocation;

    

    @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "organisation_id")
    @JsonManagedReference
    private List<Task> tasks = new ArrayList<>();

}
