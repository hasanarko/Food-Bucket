package com.example.mrcorbin.testing;

public class SearchItem {
    private String countryName;
    private int flagImage;
    private String type;

    public SearchItem(String countryName, int flagImage,String type) {
        this.countryName = countryName;
        this.flagImage = flagImage;
        this.type = type;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getFlagImage() {
        return flagImage;
    }

    public String getType() {
        return type;
    }
}
