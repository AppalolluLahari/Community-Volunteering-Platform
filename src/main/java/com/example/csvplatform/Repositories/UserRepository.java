package com.example.csvplatform.repositories;

import com.example.csvplatform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
