package com.github.k1melo.crudapi.controller;

import com.github.k1melo.crudapi.model.entitie.Product;
import com.github.k1melo.crudapi.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping
    public Product postProducts(@RequestBody Product product) {
        return productRepository.save(product);
    }
}
