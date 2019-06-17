package com.geekbrains.teryaevs.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "boot", name = "statistic")
public class ProductStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int views;

    public ProductStatistic(Product product, int views) {
        this.product = product;
        this.views = views;
    }
}
