package com.learning.models.repositories;

import com.learning.models.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>{

    Iterable<Product> findByNameContains(String name);

}
