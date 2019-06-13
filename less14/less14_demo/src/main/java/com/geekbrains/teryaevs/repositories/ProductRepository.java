package com.geekbrains.teryaevs.repositories;

import com.geekbrains.teryaevs.entities.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Iterable<Product> findAll(Sort sort);

    Iterable<Product> findAllByTitleContainsAndPriceBetween(String titlesPart, double min, double max);
}