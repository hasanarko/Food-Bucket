package com.example.mrcorbin.testing;

public class MenuItem {

    private int cuisineId;
    private String cuisineName;
    private String totalRest;

    public MenuItem() {
        // default
    }

    public MenuItem(int cuisineId, String cuisineName) {
        this.cuisineId = cuisineId;
        this.cuisineName = cuisineName;
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

    public String getTotalRest() {
        return totalRest;
    }

    public void setTotalRest(String totalRest) {
        this.totalRest = totalRest;
    }
}
