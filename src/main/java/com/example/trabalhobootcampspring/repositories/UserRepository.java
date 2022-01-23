package com.example.trabalhobootcampspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trabalhobootcampspring.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
