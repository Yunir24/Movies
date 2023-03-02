package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieAnswerServer {



    @SerializedName("docs")
    private List<Movie> movies;

    public MovieAnswerServer(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MovieAnswerServer{" +
                "movies=" + movies +
                '}';
    }
}
