package com.github.k1melo.crudapi.model.repository;

import com.github.k1melo.crudapi.model.entitie.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
