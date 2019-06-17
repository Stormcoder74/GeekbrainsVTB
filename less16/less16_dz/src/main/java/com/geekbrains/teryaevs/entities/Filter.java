package com.geekbrains.teryaevs.entities;

import lombok.Data;

@Data
public class Filter {
    private String titlesPart;
    private double priceMin;
    private double priceMax;

    public Filter() {
        titlesPart = "";
        priceMin = 0;
        priceMax = 0;
    }

    public String getTitlesPart() {
        return titlesPart;
    }
}
