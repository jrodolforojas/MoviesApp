package com.example.moviesapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Movie> movies = new ArrayList<>();
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        getDataFromFirebase();

//        //MyApadter myApadter = new MyApadter(this,movies);
//        WebScraping webScraping = new WebScraping();
//        Movie newMovie = new Movie();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                webScraping.getRottenTomatoesRating(newMovie);
//                webScraping.getGooglePlayInfo(newMovie);
//                webScraping.getAppleTVInfo(newMovie);
//                printMovieInfo(newMovie);
//                movies.add(newMovie);
//            }
//        }).start();


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //listView.setAdapter(myApadter);
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

    private final class Parallel extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    private void getDataFromFirebase(){
        database.child("movies").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:
                     snapshot.getChildren()) {
                    String tomato = dataSnapshot.child("tomato").getValue().toString();
                    Log.d("FIREBASE", tomato);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}