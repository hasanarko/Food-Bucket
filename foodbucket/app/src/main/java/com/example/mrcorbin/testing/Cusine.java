package com.example.mrcorbin.testing;

public class Cusine {

    private int cuisineId;
    private String cuisineName;
    private int cuisinePics;
    public Cusine() {
        // default
    }

    public Cusine(int cuisineId, String cuisineName) {
        this.cuisineId = cuisineId;
        this.cuisineName = cuisineName;
    }

    public Cusine(int cuisineId, String cuisineName, int cuisinePics) {
        this.cuisineId = cuisineId;
        this.cuisineName = cuisineName;
        this.cuisinePics = cuisinePics;
    }

    public Cusine(String cuisineName, int cuisinePics) {
        this.cuisineName = cuisineName;
        this.cuisinePics = cuisinePics;
    }

    public void setCuisineId(int cuisineId) {
        this.cuisineId = cuisineId;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    public int getCuisineId() {
        return cuisineId;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public int getCuisinePics() {
        return cuisinePics;
    }

    public void setCuisinePics(int cuisinePics) {
        this.cuisinePics = cuisinePics;
    }
}

