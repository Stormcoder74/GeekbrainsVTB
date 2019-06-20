package com.flamexander.cloud.client;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductDataSourceImpl implements ProductDataSource {
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Override
    public Product product(Long id) {
        return productsService.getById(id);
    }

    @Override
    public Iterable<Product> getProducts() {
        return productsService.getAll();
    }

    @Override
    public void save(Long id, String title, Double price) {
        Product product = new Product(id == 0 ? null : id, title, price);
        productsService.save(product);
    }

    @Override
    public void delete(Long id) {
        productsService.delete(id);
    }
}
