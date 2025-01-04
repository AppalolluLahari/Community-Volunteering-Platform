package com.example.csvplatform.Entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "organisation")
public class Organisation {

    @Id
    @Column(name = "organisation_id")
    private int organisationId;

    @Column(name = "organisation_website", length = 50)
    private String organisationWebsite;

    @Column(name = "organisation_location", length = 100)
    private String organisationLocation;

}
