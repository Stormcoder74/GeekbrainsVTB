package com.flamexander.cloud.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("geek-spring-cloud-eureka-client")
public interface GreetingClient {
    @GetMapping("/product/{id}")
    Product getProduct(@PathVariable(value = "id") Long id);

    @GetMapping("/product")
    List<Product> getProducts();

    @PostMapping("/product/save")
    void saveProduct(@RequestParam(value = "id") Long id,
                     @RequestParam(value = "title") String title,
                     @RequestParam(value = "price") Double price);

    @DeleteMapping("/product/delete/{id}")
    void deleteProduct(@PathVariable(value = "id") Long id);
}
