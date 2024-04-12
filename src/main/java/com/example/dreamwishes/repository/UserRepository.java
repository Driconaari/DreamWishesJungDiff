package com.example.dreamwishes.repository;

import com.example.dreamwishes.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    // Custom query method to find a user by username
    Users findByUsername(String username);

    // Custom query method to find users by age
    List<Users> findByAge(int age);

    // Custom query method to find users by age greater than
    List<Users> findByAgeGreaterThan(int age);

    // Custom query method to find users by age less than
    List<Users> findByAgeLessThan(int age);

    // Custom query method to find users by age between
    List<Users> findByAgeBetween(int start, int end);

    // Custom query method to find users by last name and age
    List<Users> findByLastNameAndAge(String lastName, int age);

    // Custom query method to find users by first name or last name
    List<Users> findByFirstNameOrLastName(String firstName, String lastName);

    // Custom query method to find users by first name like
    List<Users> findByFirstNameLike(String firstName);

    // Custom query method to count users by age
    long countByAge(int age);

    // Custom query method to delete users by age
    void deleteByAge(int age);
}
