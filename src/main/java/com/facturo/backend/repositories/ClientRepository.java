package com.facturo.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facturo.backend.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
