package com.geekbrains.teryaevs.services;

import com.geekbrains.teryaevs.entities.Filter;
import com.geekbrains.teryaevs.entities.Product;
import com.geekbrains.teryaevs.repositories.ProductRepository;
import com.geekbrains.teryaevs.services.specifications.ProductSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {
    private static final int ROWS_ON_PAGE = 5;
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElse(new Product(0L, "none", 0d));
    }

    public void save(Product product) {
            productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> getAllProducts(Filter filter, int page) {
        Specification<Product> spec = Specification.where(null);
        if (filter != null) {
            if (!filter.getTitlesPart().equals("")) {
                spec = spec.and(ProductSpecs.titleContains(filter.getTitlesPart()));
            }
            if (filter.getPriceMin() != 0) {
                spec = spec.and(ProductSpecs.priceGreaterThanOrEq(filter.getPriceMin()));
            }
            if (filter.getPriceMax() != 0) {
                spec = spec.and(ProductSpecs.priceLesserThanOrEq(filter.getPriceMax()));
            }
        }
        return productRepository.findAll(spec, PageRequest.of(page, ROWS_ON_PAGE, Sort.by("id")));
    }
}
