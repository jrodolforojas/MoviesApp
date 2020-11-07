package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
                webScraping.getRottenTomatoesRating2(newMovie);
//                tomatoMeter.setText(newMovie.getTomatoMeter());
                Log.d("MainActivity","RATING: " + newMovie.getTomatoMeter());
            }
        }).start();

//        webScraping.getRottenTomatoesRating2(newMovie);
//        webScraping.getAmazonInfo(newMovie);
//        tomatoMeter.setText(newMovie.);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Document doc = Jsoup.connect("https://www.amazon.com/Spider-Man-Far-Home-Tom-Holland/dp/B07TKZQFJC").get();
//                    Element divMovie = doc.selectFirst("div.abwJ5F tFxybk &lowbar2LF&lowbar6p &lowbar2cx-XY");
//                    String amazonPrice = divMovie.selectFirst("span.&lowbar;36qUej").text();
//                    Log.d("WebScraping", "AMAZON PRICE: " + amazonPrice);
//                    newMovie.setAmazonPrice(amazonPrice);
//
//                } catch (Exception exception){
//                    Log.d("MainActivity", exception.getMessage());
//                }
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        tomatoMeter.setText(newMovie.getAmazonPrice());
//                    }
//                });
//
//            }
//        }).start();

    }

    

}