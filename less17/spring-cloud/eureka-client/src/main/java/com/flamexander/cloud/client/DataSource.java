package com.flamexander.cloud.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public interface DataSource {
    @RequestMapping("/greeting/{id}")
    Product greeting(@PathVariable Long id);
}