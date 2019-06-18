package com.flamexander.cloud.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public interface ProductRepository {
    @RequestMapping("/product/{id}")
    Product getProduct(@PathVariable Long id);
}
