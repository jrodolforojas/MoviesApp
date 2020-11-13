package com.example.moviesapp;

public class FirebaseConnection {
    private String apple;
    private String google;
    private String tomato;

    public FirebaseConnection(String apple, String google, String tomato) {
        this.apple = apple;
        this.google = google;
        this.tomato = tomato;
    }

    public String getApple() {
        return apple;
    }

    public void setApple(String apple) {
        this.apple = apple;
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public String getTomato() {
        return tomato;
    }

    public void setTomato(String tomato) {
        this.tomato = tomato;
    }
}
