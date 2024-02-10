package com.github.k1melo.crudapi.controller;

import com.github.k1melo.crudapi.model.entitie.Client;
import com.github.k1melo.crudapi.model.entitie.Product;
import com.github.k1melo.crudapi.model.repository.ProductRepository;
import com.github.k1melo.crudapi.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<String> getProductById(@PathVariable Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(product.getAll());
    }

    @PostMapping
    public ResponseEntity<String> postProducts(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product " + product.getName() + " was created");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateProducts(@PathVariable Long id, @RequestBody Product product) {
        Product currentProduct = this.productRepository.findById(id).orElseThrow(RuntimeException::new);
        if (currentProduct == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not found");

        Utils.copyNonNullProperties(product, currentProduct);
        Product updateProduct = this.productRepository.save(currentProduct);

        return ResponseEntity.ok("Client " + updateProduct.getName() + " updated");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteProducts(@PathVariable Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(RuntimeException::new);
        productRepository.delete(product);
        return ResponseEntity.ok("Client " + product.getName() + " was deleted");
    }
}
