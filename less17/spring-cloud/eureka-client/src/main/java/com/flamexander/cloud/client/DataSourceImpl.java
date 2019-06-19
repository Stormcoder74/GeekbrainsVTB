package com.flamexander.cloud.client;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataSourceImpl implements DataSource {
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${userValue}")
    private String username;

    @Override
    public Product greeting(Long id) {
        return new Product(id, "Orange", 45.5);
    }

    @GetMapping("/abc")
    public void test() {
        System.out.println(username);
    }


    @GetMapping("/abcr")
    public String abcr() {
        return "ABC";
    }
}
