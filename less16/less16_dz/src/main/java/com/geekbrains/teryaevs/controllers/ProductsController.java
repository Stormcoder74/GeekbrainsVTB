package com.geekbrains.teryaevs.controllers;

import com.geekbrains.teryaevs.entities.Filter;
import com.geekbrains.teryaevs.entities.Product;
import com.geekbrains.teryaevs.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showProductsList(Model model, @ModelAttribute(value = "filter") Filter filter,
                                   @RequestParam(name = "page", required = false) Integer page) {
        if (page == null)
            page = 0;
        model.addAttribute("products", productsService.getAllProducts(filter, page));
        model.addAttribute("filter", filter);
        return "products";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product-page";
    }

    @GetMapping("/edit/{id}")
    public String editOneProduct(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("product", productsService.getById(id));
        return "product-page";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("product", productsService.getById(id));
        return "show-product";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productsService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String editOneProduct(@PathVariable(value = "id") Long id) {
        productsService.delete(id);
        return "redirect:/products";
    }
}
