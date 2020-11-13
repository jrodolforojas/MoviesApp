package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {

    ImageView postView;
    TextView titleView;
    TextView categoryView;
    TextView yearView;
    TextView ratingView;
    TextView tomatoView;
    TextView durationView;
    Button appleView;
    Button googleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        loadData(intent);
    }

    private void loadData(Intent intent){
        postView = findViewById(R.id.post);
        titleView = findViewById(R.id.title);
        categoryView = findViewById(R.id.category);
        yearView = findViewById(R.id.year);
        ratingView = findViewById(R.id.rating);
        tomatoView = findViewById(R.id.tomato);
        durationView = findViewById(R.id.duration);
        appleView = findViewById(R.id.appleButton);
        googleView = findViewById(R.id.googleButton);

        String post = intent.getStringExtra("post");
        String title = intent.getStringExtra("title");
        String category = intent.getStringExtra("category");
        String year = intent.getStringExtra("year");
        String rating = intent.getStringExtra("rating");
        String tomato = intent.getStringExtra("tomato");
        String duration = intent.getStringExtra("duration");
        String apple = intent.getStringExtra("apple");
        String google = intent.getStringExtra("google");

        Picasso.with(MovieActivity.this).load(post).into(postView);
        titleView.setText(title);
        categoryView.setText(category);
        yearView.setText(year);
        ratingView.setText(rating);
        tomatoView.setText(tomato);
        durationView.setText(duration);
        appleView.setText("APPLE TV " + apple);
        googleView.setText("GOOGLE " + google);
    }
}