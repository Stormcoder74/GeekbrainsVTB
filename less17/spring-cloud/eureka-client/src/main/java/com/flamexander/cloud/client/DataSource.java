package com.flamexander.cloud.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface DataSource {
    @GetMapping("/product/{id}")
    Product product(@PathVariable Long id);

    @GetMapping("/product")
    Iterable<Product> getProducts();

    @PostMapping("/product/save")
    void save(@RequestParam("product") Product product);
}
