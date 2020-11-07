package com.example.moviesapp;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebScraping {

    private final String ROTTEN_TOMATOES;
    private final String PLAY_STORE;
    private final String AMAZON_PRIME;

    public WebScraping() {
        this.ROTTEN_TOMATOES = "https://www.rottentomatoes.com/m/spider_man_far_from_home";
        this.PLAY_STORE = "https://play.google.com/store/movies/details/" +
                "Spider_Man_Far_From_Home?id=BEgGFZFGVco.P";
        this.AMAZON_PRIME = "https://www.amazon.com/Spider-Man-Far-Home-Tom-Holland/dp/B07TKZQFJC";
    }

    public String getROTTEN_TOMATOES() {
        return ROTTEN_TOMATOES;
    }

    public String getPLAY_STORE() {
        return PLAY_STORE;
    }

    public String getAMAZON_PRIME() {
        return AMAZON_PRIME;
    }

    public void getRottenTomatoesRating(Movie movie){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Document doc = Jsoup.connect(ROTTEN_TOMATOES).get();
                    Element divRating = doc.select("div.mop-ratings-wrap__half").first();
                    String tomatoMeter = divRating.
                            select("span.mop-ratings-wrap__percentage").text();
                    movie.setTomatoMeter(tomatoMeter);

                } catch (Exception exception){
                    Log.d("MainActivity", "error with ROTTEN_TOMATOES_RATING");
                }
            }
        }).start();
    }

    public void getRottenTomatoesRating2(Movie movie){
        try{
            Document doc = Jsoup.connect(ROTTEN_TOMATOES).get();
            Element divRating = doc.select("div.mop-ratings-wrap__half").first();
            String tomatoMeter = divRating.
                    select("span.mop-ratings-wrap__percentage").text();
            movie.setTomatoMeter(tomatoMeter);

        } catch (Exception exception){
            Log.d("MainActivity", "error with ROTTEN_TOMATOES_RATING");
        }
    }


    public void getAmazonInfo(Movie movie){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Document doc = Jsoup.connect(AMAZON_PRIME).get();
                    Element divMovie = doc.selectFirst("div.abwJ5F tFxybk _2LF_6p _2cx-XY");
                    String amazonPrice = divMovie.selectFirst("span._36qUej").text();
                    Log.d("WebScraping", "AMAZON PRICE: " + amazonPrice);
                    movie.setAmazonPrice(amazonPrice);

                } catch (Exception exception){
                    Log.d("WebScraping", "error getting Amazon Info");
                }
            }
        }).start();
    }


}
