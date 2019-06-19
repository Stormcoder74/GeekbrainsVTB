package com.flamexander.cloud.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public Page<Product> getAll(int page) {
        return productRepository.findAll(PageRequest.of(page, ROWS_ON_PAGE, Sort.by("id")));
    }
}
