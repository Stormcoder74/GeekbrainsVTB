package com.flamexander.cloud.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {
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

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }
}
