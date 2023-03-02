package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Videos {

    @SerializedName("trailers")
    private List<Trailer> trailers;

    @Override
    public String toString() {
        return "Videos{" +
                "trailers=" + trailers +
                '}';
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public Videos(List<Trailer> trailers) {
        this.trailers = trailers;
    }
}
