package com.example.mrcorbin.testing;

public class gallery {
    private int imageId;
    private byte[] imagePhoto;
    private int Restaurant_id;

    public gallery() {

    }

    public gallery(int imageId, byte[] imagePhoto, int restaurant_id) {
        this.imageId = imageId;
        this.imagePhoto = imagePhoto;
        Restaurant_id = restaurant_id;
    }

    public gallery(byte[] imagePhoto) {
        this.imagePhoto = imagePhoto;
    }

    // setter

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setImagePhoto(byte[] imagePhoto) {
        this.imagePhoto = imagePhoto;
    }

    public void setRestaurant_id(int restaurant_id) {
        Restaurant_id = restaurant_id;
    }

    // getter
    public int getImageId() {
        return imageId;
    }

    public byte[] getImagePhoto() {
        return imagePhoto;
    }

    public int getRestaurant_id() {
        return Restaurant_id;
    }
}
