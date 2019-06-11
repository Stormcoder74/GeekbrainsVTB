package com.geekbrains.teryaevs.services;

import com.geekbrains.teryaevs.enttities.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class SimpleProductService implements ProductService {
    private List<Product> productList;

    @PostConstruct
    private void initProductList() {
        productList = Arrays.asList(
                new Product(1, "milk", 65),
                new Product(2, "bread", 45),
                new Product(3, "butter", 145),
                new Product(4, "pork", 280),
                new Product(5, "beef", 320),
                new Product(6, "sausage", 650),
                new Product(7, "beer", 180),
                new Product(8, "coffee", 170),
                new Product(9, "sugar", 55),
                new Product(10, "tea", 120)
        );
    }

    @Override
    public void printAll(){
        System.out.println();
        for (Product product : productList){
            System.out.println(product);
        }
    }

    @Override
    public Product findByTitle(String title){
        return productList.get(productList.indexOf(new Product(0, title, 0)));
    }
}