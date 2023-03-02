package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewAnswerServer {


    @SerializedName("docs")
    private List<Review> reviewList;

    public ReviewAnswerServer(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    @Override
    public String toString() {
        return "ReviewAnswerServer{" +
                "reviewList=" + reviewList +
                '}';
    }
}
