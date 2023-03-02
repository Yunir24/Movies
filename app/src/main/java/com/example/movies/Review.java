package com.example.movies;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("type")
    private String type;
    @SerializedName("author")
    private String authorName;
    @SerializedName("review")
    private String authorReview;

    public Review(String type, String authorName, String authorReview) {
        this.type = type;
        this.authorName = authorName;
        this.authorReview = authorReview;
    }

    public String getUserType() {
        return type;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorReview() {
        return authorReview;
    }

    @Override
    public String toString() {
        return "Review{" +
                "type=" + type +
                ", authorName='" + authorName + '\'' +
                ", authorReview='" + authorReview + '\'' +
                '}';
    }
}
