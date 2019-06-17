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
    @Column(name = "product_id")
    private Long productId;
    private int views;

    public ProductStatistic(Long productId, int views) {
        this.productId = productId;
        this.views = views;
    }
}
