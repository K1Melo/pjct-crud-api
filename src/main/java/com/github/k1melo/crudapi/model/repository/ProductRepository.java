package com.github.k1melo.crudapi.model.repository;

import com.github.k1melo.crudapi.model.entitie.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>  {
}
