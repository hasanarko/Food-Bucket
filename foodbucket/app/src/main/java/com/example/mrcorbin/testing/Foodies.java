package com.example.mrcorbin.testing;

public class Foodies {
    private String foodiesId;
    private String email;
    private String name;
    private String password;
    private String phoneNumber;

    public Foodies(){}

    public Foodies(String foodiesId, String email, String name, String password, String phoneNumber) {
        this.foodiesId = foodiesId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getFoodiesId() {
        return foodiesId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
