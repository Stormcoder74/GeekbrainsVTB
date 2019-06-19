package com.flamexander.cloud.client;

import org.springframework.web.bind.annotation.*;

public interface DataSource {
    @GetMapping("/product/{id}")
    Product product(@PathVariable Long id);

    @GetMapping("/product")
    Iterable<Product> getProducts();

    @PostMapping("/product/save")
    void save(@RequestParam("product") Product product);

    @DeleteMapping("/product/delete/{id}")
    void delete(@PathVariable Long id);
}
