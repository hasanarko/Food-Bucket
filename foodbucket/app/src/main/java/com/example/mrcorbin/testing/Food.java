package com.example.mrcorbin.testing;

public class Food {

    private int foodId;
    private String foodName;
    private double foodPrice;
    private double foodRating;
    private String foodDescription;

    public Food() {
        // default
    }

    public Food(int foodId, String foodName, double foodPrice, double foodRating, String foodDescription) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodRating = foodRating;
        this.foodDescription = foodDescription;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public void setFoodRating(double foodRating) {
        this.foodRating = foodRating;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public double getFoodRating() {
        return foodRating;
    }

    public String getFoodDescription() {
        return foodDescription;
    }
}
