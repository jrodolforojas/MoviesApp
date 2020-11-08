package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tomatoMeter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tomatoMeter = findViewById(R.id.textView);
        WebScraping webScraping = new WebScraping();
        Movie newMovie = new Movie();

        new Thread(new Runnable() {
            @Override
            public void run() {
                webScraping.getRottenTomatoesRating(newMovie);
                webScraping.getGooglePlayInfo(newMovie);
                webScraping.getAppleTVInfo(newMovie);
                printMovieInfo(newMovie);
            }
        }).start();
    }

    private void printMovieInfo(Movie movie){
        Log.d("Main Activity", "Name: " + movie.getName() + "\n" +
                "TomatoMeter: " + movie.getTomatoMeter() + "\n" +
                "Rating: " + movie.getRating() + "\n" +
                "Duration: " + movie.getDuration() + "\n" +
                "GP Price: " + movie.getGooglePlayPrice() + "\n" +
                "AppleTV Price: " + movie.getAppleTVPrice() + "\n" +
                "Post: " + movie.getPostSrc() + "\n" +
                "Year: " + movie.getYear() + "\n" +
                "Synopsis: " + movie.getSynopsis() + "\n" +
                "CATEGORY: " + "FALTA" + "\n" +
                "Video: " + "\n"
        );
    }

}