package com.example.moviesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Movie> movies;

    public MainAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        if (view == null){
            view = inflater.inflate(R.layout.row, null);

        }

        TextView title = view.findViewById(R.id.title);
        TextView price = view.findViewById(R.id.price);
        ImageView imageView = view.findViewById(R.id.image);
        TextView category = view.findViewById(R.id.category);
        title.setText(movies.get(i).getName());
        price.setText(movies.get(i).getAppleTVPrice());
        category.setText(movies.get(i).getCategory());

        Picasso.with(context).load(movies.get(i).getPostSrc()).into(imageView);

        return view;
    }
}
