package com.geekbrains.teryaevs;

import com.geekbrains.teryaevs.configs.AppConfig;
import com.geekbrains.teryaevs.enttities.Cart;
import com.geekbrains.teryaevs.services.OrderService;
import com.geekbrains.teryaevs.services.ProductService;
import com.geekbrains.teryaevs.services.SimpleOrderService;
import com.geekbrains.teryaevs.services.SimpleProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductService productService = context.getBean("simpleProductService", SimpleProductService.class);
        Cart cart = context.getBean("cart", Cart.class);
        OrderService orderService = context.getBean("simpleOrderService", SimpleOrderService.class);

        productService.printAll();

        cart.add(productService.findByTitle("bread"));
        cart.add(productService.findByTitle("sausage"));
        cart.add(productService.findByTitle("coffee"));
        cart.add(productService.findByTitle("sugar"));

        orderService.makeOrder();
    }
}
