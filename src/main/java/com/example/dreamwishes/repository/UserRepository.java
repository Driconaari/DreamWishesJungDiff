package com.example.dreamwishes.repository;

import com.example.dreamwishes.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    // Custom query method to find a user by username
    Users findByUsername(String username);

}
