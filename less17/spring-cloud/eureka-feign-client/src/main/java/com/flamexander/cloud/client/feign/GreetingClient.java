package com.flamexander.cloud.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("geek-spring-cloud-eureka-client")
public interface GreetingClient {
    @GetMapping("/product/{id}")
    Product greeting(@PathVariable(value = "id") Long id);

    @GetMapping("/product")
    List<Product> getProducts();
}
