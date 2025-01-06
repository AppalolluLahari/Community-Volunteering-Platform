package com.example.csvplatform.Repositories;

import com.example.csvplatform.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
