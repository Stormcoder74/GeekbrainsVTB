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
    private Filter filter;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Autowired
    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product") Product product) {
        productsService.add(product);
        return "redirect:/products";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        model.addAttribute("product", product);
        return "product-page";
    }

    @GetMapping
    public String showProductsList(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("products", productsService.getAllProducts());
        model.addAttribute("filter", filter);
        return "products";
    }

    @GetMapping("/edit/{id}")
    public String editOneProduct(Model model,
                                 @PathVariable(value = "id") Long id) {
        model.addAttribute("product", productsService.getById(id));
        model.addAttribute("products", productsService.getAllProducts());
        model.addAttribute("filter", filter);
        return "products";
    }

    @PostMapping("/filter")
    public String showFilteredProductsList(Model model,
                                           @ModelAttribute(value = "filter") Filter filter) {
        copyFilter(filter);
        model.addAttribute("product", new Product());
        model.addAttribute("products", productsService.getFilteredProducts(filter));
        model.addAttribute("filter", filter);
        return "products";
    }

    private void copyFilter(Filter filter){
        this.filter.setTitlesPart(filter.getTitlesPart());
        this.filter.setPriceMin(filter.getPriceMin());
        this.filter.setPriceMax(filter.getPriceMax());
    }
}
