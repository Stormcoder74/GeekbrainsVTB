package com.geekbrains.teryaevs.services;

import com.geekbrains.teryaevs.entities.Filter;
import com.geekbrains.teryaevs.entities.Product;
import com.geekbrains.teryaevs.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void add(Product product) {
        if (!product.getTitle().equals("") &&
                product.getPrice() > 0) {
            productRepository.save(product);
        }
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElse(new Product(0L, "none", 0d));
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }

    public Iterable<Product> getFilteredProducts(Filter filter) {
        return productRepository.findAllByTitleContainsAndPriceBetween(
                filter.getTitlesPart(), filter.getPriceMin(), filter.getPriceMax());
    }

    public void deleteOne(Long id) {
        productRepository.deleteById(id);
    }
}
