package com.geekbrains.teryaevs.services;

import com.geekbrains.teryaevs.enttities.Cart;
import com.geekbrains.teryaevs.enttities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleOrderService implements OrderService {
    private Cart cart;

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void makeOrder() {
        double totalCost = 0;

        System.out.println("\nOrder contains:");
        System.out.println("--------------------");
        for (Product product : cart.getProducts()) {
            System.out.println(product);
            totalCost += product.getCost();
        }
        System.out.println("--------------------");
        System.out.println("total cost is: " + totalCost);
    }
}
