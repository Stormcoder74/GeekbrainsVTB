package com.geekbrains.teryaevs.enttities;

import java.util.Objects;

public class Product {
    private long id;
    private String title;
    private double cost;

    public Product(long id, String title, double cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return title + ": " + cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(title, product.title);
    }
}
