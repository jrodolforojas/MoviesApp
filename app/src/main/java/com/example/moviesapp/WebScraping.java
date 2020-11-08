package com.example.moviesapp;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScraping {

    private final String ROTTEN_TOMATOES;
    private final String PLAY_STORE;
    private final String APPLETV;

    public WebScraping() {
        this.ROTTEN_TOMATOES = "https://www.rottentomatoes.com/m/Aquaman";
        this.PLAY_STORE = "https://play.google.com/store/movies/details/Aquaman?id=dvbliOZgQoE.P";
        this.APPLETV = "https://itunes.apple.com/us/movie/aquaman-2018/id1444244278";

    }

    public String getROTTEN_TOMATOES() {
        return ROTTEN_TOMATOES;
    }

    public String getPLAY_STORE() {
        return PLAY_STORE;
    }

    public String getAPPLETV() {
        return APPLETV;
    }

    public void getRottenTomatoesRating(Movie movie){
        try{
            Document doc = Jsoup.connect(ROTTEN_TOMATOES).get();
            Element divRating = doc.select("div.mop-ratings-wrap__half").first();
            String tomatoMeter = divRating.
                    select("span.mop-ratings-wrap__percentage").text();
            movie.setTomatoMeter(tomatoMeter);

            String rating = doc.select("span.mop-ratings-wrap__percentage").get(1).text();
            movie.setRating(rating);

        } catch (Exception exception){
            Log.d("MainActivity", "error with ROTTEN_TOMATOES_RATING");
        }
    }

    public void getGooglePlayInfo(Movie movie){
        try{
            Document document = Jsoup.connect(PLAY_STORE).get();

            // Get post Source
            Element divPost = document.selectFirst("div.hkhL9e");
            String postSrc = divPost.selectFirst("img").attr("src");
            movie.setPostSrc(postSrc);

            // Get movie's name
            Element divName = document.selectFirst("h1.AHFaub");
            String name = divName.select("span").text();
            movie.setName(name);

            // Get movie's year and duration
            Elements divYearDuration = document.select("span.UAO9ie");
            String year = divYearDuration.select("span.UAO9ie").get(0).text();
            movie.setYear(year);

            String duration = divYearDuration.select("span.UAO9ie").get(1).
                    selectFirst("span").text();
            movie.setDuration(duration);

            // Get movie's google play price
            Element divPrice = document.selectFirst("button.LkLjZd.ScJHi.HPiPcc.IfEcue");
            String googlePlayPrice = divPrice.text();
            movie.setGooglePlayPrice(googlePlayPrice);

            //Get movie's video
            Element divVideo = document.selectFirst("div.TdqJUe");
            String video = divVideo.selectFirst("button").attr("data-trailer-url");
            if (!video.equals("")){
                movie.addMediaItem(video);
            }

            //Get movie category
            Element divCategory = document.selectFirst("div.T9A3Nb");
            String category = divCategory.selectFirst("a").text();
            movie.setCategory(category);

            // Get movie synopsis
            Element divSynopsis = document.selectFirst("div.DWPxHb");
            String synopsis = divSynopsis.selectFirst("span").text();
            movie.setSynopsis(synopsis);


        }
        catch (Exception exception){
            Log.d("WebScrapingGOOGLEPLAY", exception.getMessage());
        }
    }

    public void getAppleTVInfo(Movie movie){
        try{
            Document document = Jsoup.connect(APPLETV).get();
            // Get apple price
            Element appleTV = document.selectFirst("li.inline-list__item.inline-list__item--slashed");
            String applePrice = appleTV.select("span").text();
            movie.setAppleTVPrice(applePrice);

            // Get apple trailer
            Element divAppleVideo = document.selectFirst("div.we-video-thumbnail__control ");
            String appleVideo = divAppleVideo.selectFirst("button").
                    attr("data-ac-films-href");
            if (!appleVideo.equals("")){
                movie.addMediaItem(appleVideo);
            }
            else{
                Log.d("APPLE", "XD");
            }

        }
        catch (Exception exception){
            Log.d("WebScrapingVUDU", exception.getMessage());
        }
    }

}
