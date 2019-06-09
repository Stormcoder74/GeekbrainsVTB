package com.geekbrains.teryaevs.entities;

import javax.persistence.*;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "consumers_id")
    private Consumer consumer;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Product product;

    @Column(name = "price_of_purchase")
    private double price;

    public Purchase() {
    }

    public Purchase(Consumer consumer, Product product, double price) {
        this.consumer = consumer;
        this.product = product;
        this.price = price;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
