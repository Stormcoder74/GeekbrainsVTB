package com.geekbrains.teryaevs.repositories;

import com.geekbrains.teryaevs.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
