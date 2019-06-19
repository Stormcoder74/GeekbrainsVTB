package com.flamexander.cloud.client;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataSourceImpl implements DataSource {
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

//    @Value("${spring.application.name}")
//    private String appName;
//
//    @Value("${userValue}")
//    private String username;

    @Override
    public Product product(Long id) {
        return productsService.getById(id);
    }

    @Override
    public Iterable<Product> getProducts() {
        return productsService.getAll();
    }

    @Override
    public void save(Product product) {
        productsService.save(product);
    }
}
