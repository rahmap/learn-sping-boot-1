package com.learning.controllers;

import com.learning.DTO.ResponseData;
import com.learning.models.entities.Product;
import com.learning.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ResponseData<Product>> create (@Valid @RequestBody Product product, Errors errors) {

        ResponseData<Product> responseData = new ResponseData<>();
        try {
            if(errors.hasErrors()) {
                for (FieldError fieldError : errors.getFieldErrors()) {
                    System.out.println(fieldError.getDefaultMessage());
                    // responseData.getMessages().put(fieldError.getField(), fieldError.getDefaultMessage());
                    responseData.setMessage(fieldError.getDefaultMessage());
                }
                responseData.setStatus(false);

                // throw new RuntimeException("Validation Errors");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }
            responseData.setStatus(true);
            responseData.setMessage("Product created successfully");
            responseData.setResult(productService.create(product));

            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.setMessage("Failed created product");

            return ResponseEntity.ok(responseData);
        }
    }

    @GetMapping
    public Iterable<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product find(@PathVariable("id") Long id) {
        return productService.find(id);
    }

    @GetMapping("/search?name={name}")
    public Iterable<Product> findByName(@PathVariable("name") String name) {
        return productService.findByName(name);
    }
}
