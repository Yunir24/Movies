package com.example.movies;

import com.google.gson.annotations.SerializedName;

public class MovieTrailerAnswerServer {

    @SerializedName("videos")
    private Videos videos;

    public Videos getVideos() {
        return videos;
    }

    public MovieTrailerAnswerServer(Videos videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "MovieTrailerAnswerServer{" +
                "videos=" + videos +
                '}';
    }
}
