package com.example.trabalhobootcampspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trabalhobootcampspring.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
