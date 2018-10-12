package com.example.mrcorbin.testing;

public class NewlyOpened {
    private int restId;
    private String restName;
    private String restEmail;
    private String restPassword;
    private String restOwnerType;
    private int restPhone;
    private String restAddress;
    private String restAccountStatus;
    private byte[] restImage;
    private String restRating;
    private String restStatus;
    private String restOpeningTime;
    private String restClosingTime;
    private double foodPrice;

    //NewlyOpened(int id, String name, String email, String password, String ownerType, int phone, String address, String accountStatus, byte[] img, String s, String restStatus, String openingTime, String closingTime){}

    public NewlyOpened(int restId, String restName, String restEmail, String restPassword, String restOwnerType, int restPhone, String restAddress, String restAccountStatus, byte[] restImage, String restRating, String restStatus, String restOpeningTime, String restClosingTime) {
        this.restId = restId;
        this.restName = restName;
        this.restEmail = restEmail;
        this.restPassword = restPassword;
        this.restOwnerType = restOwnerType;
        this.restPhone = restPhone;
        this.restAddress = restAddress;
        this.restAccountStatus = restAccountStatus;
        this.restImage = restImage;
        this.restRating = restRating;
        this.restStatus = restStatus;
        this.restOpeningTime = restOpeningTime;
        this.restClosingTime = restClosingTime;

    }

    public int getRestId() {
        return restId;
    }

    public String getRestName() {
        return restName;
    }

    public String getRestEmail() {
        return restEmail;
    }

    public String getRestPassword() {
        return restPassword;
    }

    public String getRestOwnerType() {
        return restOwnerType;
    }

    public int getRestPhone() {
        return restPhone;
    }

    public String getRestAddress() {
        return restAddress;
    }

    public String getRestAccountStatus() {
        return restAccountStatus;
    }

    public byte[] getRestImage() {
        return restImage;
    }

    public String getRestRating() {
        return restRating;
    }

    public String getRestStatus() {
        return restStatus;
    }

    public String getRestOpeningTime() {
        return restOpeningTime;
    }

    public String getRestClosingTime() {
        return restClosingTime;
    }

    public double getFoodPrice() {
        return foodPrice;
    }


    public void setRestId(int restId) {
        this.restId = restId;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public void setRestEmail(String restEmail) {
        this.restEmail = restEmail;
    }

    public void setRestPassword(String restPassword) {
        this.restPassword = restPassword;
    }

    public void setRestOwnerType(String restOwnerType) {
        this.restOwnerType = restOwnerType;
    }

    public void setRestPhone(int restPhone) {
        this.restPhone = restPhone;
    }

    public void setRestAddress(String restAddress) {
        this.restAddress = restAddress;
    }

    public void setRestAccountStatus(String restAccountStatus) {
        this.restAccountStatus = restAccountStatus;
    }

    public void setRestImage(byte[] restImage) {
        this.restImage = restImage;
    }

    public void setRestRating(String restRating) {
        this.restRating = restRating;
    }

    public void setRestStatus(String restStatus) {
        this.restStatus = restStatus;
    }

    public void setRestOpeningTime(String restOpeningTime) {
        this.restOpeningTime = restOpeningTime;
    }

    public void setRestClosingTime(String restClosingTime) {
        this.restClosingTime = restClosingTime;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }
}
