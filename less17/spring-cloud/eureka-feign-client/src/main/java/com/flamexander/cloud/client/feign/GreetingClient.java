package com.flamexander.cloud.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("geek-spring-cloud-eureka-client")
public interface GreetingClient {
    @RequestMapping("/greeting/{id}")
    String greeting(@PathVariable(value = "id") Long id);
}
