package com.flamexander.cloud.client;

import org.springframework.web.bind.annotation.*;

public interface ProductDataSource {
    @GetMapping("/product/{id}")
    Product product(@PathVariable Long id);

    @GetMapping("/product")
    Iterable<Product> getProducts();

    @PostMapping("/product/save")
    void save(@RequestParam("id") Long id,
              @RequestParam("title") String title,
              @RequestParam("price") Double price);

    @DeleteMapping("/product/delete/{id}")
    void delete(@PathVariable Long id);
}
