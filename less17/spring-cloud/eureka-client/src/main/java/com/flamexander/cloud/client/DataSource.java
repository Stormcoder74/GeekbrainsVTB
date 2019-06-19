package com.flamexander.cloud.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface DataSource {
    @GetMapping("/product/{id}")
    Product product(@PathVariable Long id);

    @GetMapping("/product")
    Iterable<Product> getProducts();
}
