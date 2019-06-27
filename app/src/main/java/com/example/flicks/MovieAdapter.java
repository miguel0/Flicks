package com.example.flicks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flicks.models.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    // INSTANCE FIELDS
    ArrayList<Movie> movies;

    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    // creates and inflates view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // get context and create inflater
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // create view using item_movie layout
        View movieView = inflater.inflate(R.layout.item_movie, viewGroup, false);
        return new ViewHolder(movieView);
    }

    // binds inflated view to a new item at a specific position
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // get data from position i
        Movie movie = movies.get(i);
        // populate view with obtained data
        viewHolder.tvTitle.setText(movie.getTitle());
        viewHolder.tvOverview.setText(movie.getOverview());
    }

    // Returns size of entire data set
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // view objects
        ImageView ivPosterImage;
        TextView tvTitle;
        TextView tvOverview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPosterImage = itemView.findViewById(R.id.ivPosterImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
        }
    }
}
