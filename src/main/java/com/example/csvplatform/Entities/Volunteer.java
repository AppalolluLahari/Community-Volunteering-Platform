package com.example.csvplatform.Entities;

import jakarta.persistence.*;
import lombok.*;



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
    private int volunteerId;

    @Column(name = "location", length = 100)
    private String location;


}
