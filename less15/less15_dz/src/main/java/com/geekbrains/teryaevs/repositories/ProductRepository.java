package com.geekbrains.teryaevs.repositories;

import com.geekbrains.teryaevs.entities.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends
        PagingAndSortingRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {

    List<Product> findAll(Sort sort);

    List<Product> findAllByTitleContainsAndPriceBetween(String titlesPart, double min, double max);
}