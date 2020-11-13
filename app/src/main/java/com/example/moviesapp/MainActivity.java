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
import android.webkit.WebBackForwardList;
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Movie> movies = new ArrayList<>();
    ArrayList<FirebaseConnection> links = new ArrayList<>();
    AsyncTask<Void,Void,String> asyncTask;
//    private final DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    private void loadData(){
        FirebaseConnection fb1 = new FirebaseConnection(
                "https://itunes.apple.com/cr/movie/spider-man-homecoming/id1243195844?l=en",
                "https://play.google.com/store/movies/details/Spider_Man_Homecoming?id=vaYRC8mIusY",
                "https://www.rottentomatoes.com/m/spider_man_homecoming"
                );
        FirebaseConnection fb2 = new FirebaseConnection(
                "https://itunes.apple.com/us/movie/aquaman-2018/id1444244278",
                "https://play.google.com/store/movies/details/Aquaman?id=dvbliOZgQoE.P",
                "https://www.rottentomatoes.com/m/Aquaman"
        );

        FirebaseConnection fb3 = new FirebaseConnection(
                "https://itunes.apple.com/us/movie/avengers-endgame/id1459467957",
                "https://play.google.com/store/movies/details/Avengers_Endgame?id=E768AD6FC39C1A0FMV",
                "https://www.rottentomatoes.com/m/avengers_endgame"
        );

        FirebaseConnection fb4 = new FirebaseConnection(
                "https://itunes.apple.com/us/movie/venom/id1437190744",
                "https://play.google.com/store/movies/details/Venom?id=3zaLCAblv4U",
                "https://www.rottentomatoes.com/m/venom_2018"
        );

        links.add(fb1);
        links.add(fb2);
        links.add(fb3);
        links.add(fb4);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        loadData();
        Log.d("Main", Integer.toString(links.size()));
        asyncTask = new LongOperation();
        asyncTask.execute();

    }

    private void printMovieInfo(){
        for(Movie movie: movies){
            Log.d("Main Activity", "\nName: " + movie.getName() + "\n" +
                    "TomatoMeter: " + movie.getTomatoMeter() + "\n" +
                    "GP Price: " + movie.getGooglePlayPrice() + "\n" +
                    "AppleTV Price: " + movie.getAppleTVPrice() + "\n");
        }
    }

//    public void getFirebaseLinks(){
//        database.child("movies").addValueEventListener(new ValueEventListener(){
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()){
//                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
//                        if (dataSnapshot.exists()){
//                            String apple = dataSnapshot.child("apple").getValue().toString();
//                            String google = dataSnapshot.child("google").getValue().toString();
//                            String tomato = dataSnapshot.child("tomato").getValue().toString();
//                            FirebaseConnection link = new FirebaseConnection(apple,google,tomato);
//                            links.add(link);
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    private final class LongOperation extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            for (FirebaseConnection link: links){
                WebScraping webScraping = new WebScraping(link.getApple(),
                        link.getGoogle(), link.getTomato());
                Movie movie = new Movie();
                webScraping.getRottenTomatoesRating(movie);
                webScraping.getGooglePlayInfo(movie);
                webScraping.getAppleTVInfo(movie);
                movies.add(movie);
            }
            return "Ejecutado";
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("POST",s);
            printMovieInfo();
        }
    }

}