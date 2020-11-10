package com.example.moviesapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        MyApadter myApadter = new MyApadter(this,movies);
        WebScraping webScraping = new WebScraping();
        Movie newMovie = new Movie();
        new Thread(new Runnable() {
            @Override
            public void run() {
                webScraping.getRottenTomatoesRating(newMovie);
                webScraping.getGooglePlayInfo(newMovie);
                webScraping.getAppleTVInfo(newMovie);
                printMovieInfo(newMovie);
                movies.add(newMovie);

            }


        }).start();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(myApadter);
            }
        });
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

    class MyApadter extends ArrayAdapter<Movie>{
        Context context;
        ArrayList<Movie> movies;

        public MyApadter(Context context, ArrayList<Movie> movies){
            super(context,R.layout.row);
            this.movies = movies;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = layoutInflater.inflate(R.layout.row, parent, false);

            ImageView image = row.findViewById(R.id.image);
            TextView title = row.findViewById(R.id.title);
            TextView category = row.findViewById(R.id.category);
            TextView price = row.findViewById(R.id.price);

            title.setText(movies.get(position).getName());
            category.setText(movies.get(position).getCategory());
            price.setText(movies.get(position).getAppleTVPrice());

            return row;
        }
    }

}