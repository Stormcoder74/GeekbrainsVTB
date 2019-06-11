package com.geekbrains.teryaevs.services;

import com.geekbrains.teryaevs.enttities.Product;

public interface ProductService {
    void printAll();
    Product findByTitle(String title);
}
