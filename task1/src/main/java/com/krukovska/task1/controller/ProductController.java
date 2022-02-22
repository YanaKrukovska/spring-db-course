package com.krukovska.task1.controller;

import com.krukovska.task1.persistence.model.Product;
import com.krukovska.task1.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        Product product = service.getById(productId);
        log.info("Found product {}", product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = service.findAll();
        log.info("Found products, list size= {}", products.size());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
