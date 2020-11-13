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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Movie> movies = new ArrayList<>();
    ArrayList<FirebaseConnection> links = new ArrayList<>();
    AsyncTask<Void,Void,String> asyncTask;
    private final DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        getFirebaseLinks();
    }

    private void printMovieInfo(){
        for(Movie movie: movies){
            Log.d("Main Activity", "\nName: " + movie.getName() + "\n" +
                    "TomatoMeter: " + movie.getTomatoMeter() + "\n" +
                    "GP Price: " + movie.getGooglePlayPrice() + "\n" +
                    "AppleTV Price: " + movie.getAppleTVPrice() + "\n");
        }
    }

    private void getFirebaseLinks(){
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.child("movies").getChildren()){
                    FirebaseConnection link = new FirebaseConnection(
                            dataSnapshot.child("apple").getValue().toString(),
                            dataSnapshot.child("google").getValue().toString(),
                            dataSnapshot.child("tomato").getValue().toString()
                    );
                    links.add(link);
                }
                if (links.size() >0){
                    asyncTask = new LongOperation();
                    asyncTask.execute();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private final class LongOperation extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            Log.d("FIREBASE", Integer.toString(links.size()));
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
            MainAdapter mainAdapter = new MainAdapter(MainActivity.this, movies);
            listView.setAdapter(mainAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(MainActivity.this,
                            "Movie: " + movies.get(i).getName(), Toast.LENGTH_SHORT);
                }
            });

        }
    }

}