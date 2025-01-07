package com.example.csvplatform.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "organisation")
public class Organisation extends User {

    private int organisationId = super.userId;

    @Column(name = "organisation_website", length = 50)
    private String organisationWebsite;

    @Column(name = "organisation_location", length = 100)
    private String organisationLocation;

    @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

}
