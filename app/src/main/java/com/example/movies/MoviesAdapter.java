package com.example.movies;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{

    private List<Movie> movies = new ArrayList<>();
    private OnReachEndListener onReachEndListener;
    private onMovieClickListener movieClickListener;

    public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void setMovieClickListener(onMovieClickListener movieClickListener) {
        this.movieClickListener = movieClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_item,
                parent,
                false
        );

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movie = movies.get(position);
        Glide.with(holder.itemView)
                .load(movie.getPoster().getUrl())
                .into(holder.imageViewPoster);
        double rating = Double.parseDouble(movie.getRating().getKp());
        int backgroungId;
        if(rating > 7){
            backgroungId = R.drawable.circle_green;
        } else if (rating > 5){
            backgroungId = R.drawable.circle_orange;
        } else {
            backgroungId = R.drawable.circle_red;
        }
        Drawable background = ContextCompat.getDrawable(holder.itemView.getContext(), backgroungId);
        holder.textViewRating.setBackground(background);
        holder.textViewRating.setText(movie.getRating().getKp());

        if(position >= movies.size()-10 && onReachEndListener != null){
            onReachEndListener.onReachEnd();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(movieClickListener != null){
                    movieClickListener.onMovieClick(movie);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    interface onMovieClickListener{

        void onMovieClick(Movie movie);
    }


    interface OnReachEndListener{

        void onReachEnd();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageViewPoster;
        private final TextView textViewRating;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            this.textViewRating = itemView.findViewById(R.id.textViewRating);
        }
    }
}
