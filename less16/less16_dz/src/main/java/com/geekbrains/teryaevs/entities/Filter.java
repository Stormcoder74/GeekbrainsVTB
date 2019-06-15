package com.geekbrains.teryaevs.entities;

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

    public void setTitlesPart(String titlesPart) {
        this.titlesPart = titlesPart;
    }

    public double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(double priceMin) {
        this.priceMin = priceMin;
    }

    public double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(double priceMax) {
            this.priceMax = priceMax;
    }
}
