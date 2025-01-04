package com.example.csvplatform.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    int userId;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "email",unique = true)
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "phone",unique = true)
    String phone;

    String role;

    @Column(name = "verified")
    boolean verified;



}
