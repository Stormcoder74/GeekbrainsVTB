package com.geekbrains.teryaevs.repositories;

import com.geekbrains.teryaevs.entities.Product;
import com.geekbrains.teryaevs.entities.ProductStatistic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepository extends CrudRepository<ProductStatistic, Long> {
    ProductStatistic findByProduct(Product product);
    List<ProductStatistic> findTop3ByOrderByViewsDesc();
}