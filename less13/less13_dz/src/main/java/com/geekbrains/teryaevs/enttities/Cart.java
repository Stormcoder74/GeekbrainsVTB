package com.geekbrains.teryaevs.enttities;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleCart implements Cart {
    List<Product> cart;

    public List<Product> getProducts() {
        return cart;
    }

    @PostConstruct
    void initCart(){
        cart = new ArrayList<>();
    }

    @Override
    public void add(Product product) {
        cart.add(product);
    }
}
