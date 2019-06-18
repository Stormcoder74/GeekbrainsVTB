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
        model.addAttribute("filter", new Filter());
        return "products";
    }

    @GetMapping("/edit/{id}")
    public String editOneProduct(Model model,
                                 @PathVariable(value = "id") Long id) {
        model.addAttribute("product", productsService.getById(id));
        model.addAttribute("products", productsService.getAllProducts());
        model.addAttribute("filter", new Filter());
        return "products";
    }

    @PostMapping("/filter")
    public String showFilteredProductsList(Model model,
                                           @ModelAttribute(value = "filter") Filter filter) {
        model.addAttribute("product", new Product());
        model.addAttribute("products", productsService.getFilteredProducts(filter));
        model.addAttribute("filter", filter);
        return "products";
    }

    //-------------------------------------------------------------------------
    // REST requests
    //-------------------------------------------------------------------------

    @PostMapping
    public void insertProduct(@RequestParam Product product) {
        productsService.add(product);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product showProduct(@PathVariable Long id) {
        return productsService.getById(id);
    }

    @RequestMapping(name = "/{id}", method = RequestMethod.DELETE)
    public int deleteProduct(@PathVariable Long id) {
        productsService.deleteOne(id);
        return 200;
    }
}
