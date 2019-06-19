package com.flamexander.cloud.client.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class GreetingController {
    private GreetingClient greetingClient;

    @Autowired
    public void setGreetingClient(GreetingClient greetingClient) {
        this.greetingClient = greetingClient;
    }

    @GetMapping("/{id}")
    public String showProduct(Model model, @PathVariable Long id) {
        Product product = greetingClient.getProduct(id);
        model.addAttribute("product", product);
        return "show-product";
    }

    @GetMapping
    public String showProductsList(Model model) {
        List<Product> products = greetingClient.getProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add_page")
    public String showAddPage(Model model) {
        model.addAttribute("product", new Product());
        return "product-page";
    }

    @GetMapping("/edit_page")
    public String showEditPage(Model model, @ModelAttribute("product") Product product) {
        model.addAttribute("product", product);
        return "product-page";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        greetingClient.saveProduct(product);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        greetingClient.deleteProduct(id);
        return "redirect:/product";
    }
}
