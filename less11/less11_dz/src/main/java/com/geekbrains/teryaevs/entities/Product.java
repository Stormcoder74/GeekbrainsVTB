package com.geekbrains.teryaevs.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_name")
    private String name;

    private int price;

    @OneToMany(mappedBy = "product",
            fetch = FetchType.EAGER)
    private List<Purchase> purchases;

    public Product() {
        this.purchases = new ArrayList<>();
    }

    public Product(String name, int price) {
        this();
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }
}
