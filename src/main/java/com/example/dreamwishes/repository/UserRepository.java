package com.example.dreamwishes.repository;

import com.example.dreamwishes.entity.Users;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@EntityScan("com.example.dreamwishes.entity")
public interface UserRepository extends JpaRepository<Users, Long> {

    // Custom query method to find a user by username
    Users findByUsername(String username);

}
