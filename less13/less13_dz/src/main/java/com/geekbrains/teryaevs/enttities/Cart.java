package com.geekbrains.teryaevs.enttities;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private List<Product> cart;

    @PostConstruct
    void initCart() {
        cart = new ArrayList<>();
    }

    public void add(Product product) {
        cart.add(product);
        System.out.println("\n" + product.getTitle() + " added to the cart");
    }

    public List<Product> getProducts() {
        return cart;
    }
}
