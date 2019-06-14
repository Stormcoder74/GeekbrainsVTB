package com.geekbrains.teryaevs.controllers;

import com.geekbrains.teryaevs.entities.Filter;
import com.geekbrains.teryaevs.entities.Product;
import com.geekbrains.teryaevs.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<Product> content = productsService.getAllProducts(filter, page).getContent();
        model.addAttribute("products", content);
        model.addAttribute("filter", filter);
        return "products";
    }
}
