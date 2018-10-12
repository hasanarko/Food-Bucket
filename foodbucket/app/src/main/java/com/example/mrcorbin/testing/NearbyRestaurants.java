package com.example.mrcorbin.testing;

public class NearbyRestaurants {

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

    NearbyRestaurants(){}

    public NearbyRestaurants(int restId, String restName, String restEmail, String restPassword, String restOwnerType, int restPhone, String restAddress, String restAccountStatus, byte[] restImage, String restRating) {
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
}
