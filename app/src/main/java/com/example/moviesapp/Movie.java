package com.example.moviesapp;

import java.util.ArrayList;

public class Movie {
    private String name;
    private String category;
    private String year;
    private String rating;
    private String tomatoMeter;
    private String duration;
    private String amazonPrice;
    private String googlePlayPrice;
    private String postSrc;
    private ArrayList<String> imagesSrc;
    private String synopsis;

    public Movie() {
        this.imagesSrc = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTomatoMeter() {
        return tomatoMeter;
    }

    public void setTomatoMeter(String tomatoMeter) {
        this.tomatoMeter = tomatoMeter;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAmazonPrice() {
        return amazonPrice;
    }

    public void setAmazonPrice(String amazonPrice) {
        this.amazonPrice = amazonPrice;
    }

    public String getGooglePlayPrice() {
        return googlePlayPrice;
    }

    public void setGooglePlayPrice(String googlePlayPrice) {
        this.googlePlayPrice = googlePlayPrice;
    }

    public String getPostSrc() {
        return postSrc;
    }

    public void setPostSrc(String postSrc) {
        this.postSrc = postSrc;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

}
