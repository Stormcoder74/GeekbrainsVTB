package com.flamexander.cloud.client.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GreetingController {
    private GreetingClient greetingClient;

    @Autowired
    public void setGreetingClient(GreetingClient greetingClient) {
        this.greetingClient = greetingClient;
    }

    @GetMapping("/product/{id}")
    public String showProduct(Model model, @PathVariable Long id) {
        Product product = greetingClient.greeting(id);
        model.addAttribute("product", product);
        return "show-product";
    }

    @GetMapping("/product")
    public String showProductsList(Model model) {
        List<Product> products = greetingClient.getProducts();
        model.addAttribute("products", products);
        return "products";
    }
}
