package com.geekbrains.teryaevs.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "boot", name = "statistic")
public class ProducStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column("")
    private Long productId;
    private int wiews;
}
