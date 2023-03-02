package com.example.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie?field=rating.kp&search=7-10&field=year&search=2017-2020&sortField=votes.imdb&sortType=-1&token=8TWNKGJ-EQN4KG4-KKCN78K-JS7JSBF&limit=4")
    Single<MovieAnswerServer> loadMovies(@Query("page") int page);


    @GET("movie?token=8TWNKGJ-EQN4KG4-KKCN78K-JS7JSBF&field=id")
    Single<MovieTrailerAnswerServer> loadTrailers(@Query("search") int movie);

    @GET("review?token=8TWNKGJ-EQN4KG4-KKCN78K-JS7JSBF&limit=5&field=movieId")
    Single<ReviewAnswerServer> loadReview(@Query("search") int movie);

}
