package com.geekbrains.teryaevs.entities;

import javax.persistence.*;

@Entity
@Table(schema = "boot", name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product() {
    }

    public Product(Long id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
}
