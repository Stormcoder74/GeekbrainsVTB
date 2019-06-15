package com.geekbrains.teryaevs.services;

import com.geekbrains.teryaevs.entities.Filter;
import com.geekbrains.teryaevs.entities.Product;
import com.geekbrains.teryaevs.repositories.ProductRepository;
import com.geekbrains.teryaevs.services.specifications.ProductSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAllProducts(Filter filter, int page) {
        Specification<Product> spec = Specification.where(null);
        if (filter != null) {
            if (!filter.getTitlesPart().equals("")) {
                spec = spec.and(ProductSpecs.titleContains(filter.getTitlesPart()));
            }
            if (filter.getPriceMin() != 0) {
                spec = spec.and(ProductSpecs.priceLesserThanOrEq(filter.getPriceMin()));
            }
            if (filter.getPriceMax() != 0) {
                spec = spec.and(ProductSpecs.priceGreaterThanOrEq(filter.getPriceMax()));
            }
        }
        return productRepository.findAll(spec, PageRequest.of(page, 2));
    }
}
