package com.example.csvplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.csvplatform.entities.User;



public interface UserRepository extends JpaRepository<User,Integer> {
}
