package com.learning.controllers;

import com.learning.models.entities.Product;
import com.learning.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product create (@RequestBody Product product) {
        return productService.create(product);
    }

    @GetMapping
    public Iterable<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product find(@PathVariable("id") Long id) {
        return productService.find(id);
    }
}
