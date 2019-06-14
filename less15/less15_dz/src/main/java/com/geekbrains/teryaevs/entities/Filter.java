package com.geekbrains.teryaevs.entities;

public class Filter {
    private String titlesPart;
    private double priceMin;
    private double priceMax;

    public Filter() {
        titlesPart = "";
        priceMin = 0;
        priceMax = Double.MAX_VALUE;
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
        if (priceMax == 0) {
            this.priceMax = Double.MAX_VALUE;
        } else {
            this.priceMax = priceMax;
        }
    }
}
