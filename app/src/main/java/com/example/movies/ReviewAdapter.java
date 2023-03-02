package com.example.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private static final String TYPE_POSITIVE = "Позитивный";
    private static final String TYPE_NEUTRAL = "Нейтральный";

    private List<Review> reviewList = new ArrayList<>();

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item,
                parent,
                false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.textReview.setText(review.getAuthorReview());
        holder.authorReview.setText(review.getAuthorName());
        String type = review.getUserType();
        int colorResId = android.R.color.holo_red_light;
        int backgroundColor;
        switch (type){
            case TYPE_POSITIVE:
                colorResId = android.R.color.holo_green_light;
                break;
            case TYPE_NEUTRAL:
                colorResId = android.R.color.holo_orange_light;
                break;
        }
        backgroundColor = ContextCompat.getColor(holder.itemView.getContext(), colorResId);
        holder.latoutReview.setBackgroundColor(backgroundColor);
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder{

        private final TextView authorReview;
        private final LinearLayout latoutReview;
        private final TextView textReview;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            this.authorReview = itemView.findViewById(R.id.textViewAutorReview);
            this.textReview = itemView.findViewById(R.id.textViewReview);
            latoutReview = itemView.findViewById(R.id.linearLayoutReview);
        }
    }
}
