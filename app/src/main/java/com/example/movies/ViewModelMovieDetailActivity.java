package com.example.movies;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ViewModelMovieDetailActivity extends AndroidViewModel {

    private MutableLiveData<List<Trailer>> trailerOfMovie = new MutableLiveData<>();
    private MutableLiveData<List<Review>> reviewOfMovie = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MovieDao movieDao;

    public ViewModelMovieDetailActivity(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    public LiveData<Movie> getFavouriteMovie(int movieId){
        return movieDao.getFavouriteMovie(movieId);
    }

    public LiveData<List<Trailer>> getTrailerOfMovie() {
        return trailerOfMovie;
    }
    public LiveData<List<Review>> getReviewOfMovie() {
        return reviewOfMovie;
    }



    public void loadTrailers(int id){
        Disposable disposable = ApiFactory.apiService.loadTrailers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<MovieTrailerAnswerServer, List<Trailer>>() {
                    @Override
                    public List<Trailer> apply(MovieTrailerAnswerServer movieTrailerAnswerServer) throws Throwable {
                        return movieTrailerAnswerServer.getVideos().getTrailers();
                    }
                })
                .subscribe(new Consumer<List<Trailer>>() {
                    @Override
                    public void accept(List<Trailer> trailerList) throws Throwable {
                        //Log.d("daut", movieTrailerAnswerServer.toString());
                        trailerOfMovie.setValue(trailerList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("daut","Ошибочка братан");
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void insertMovie(Movie movie){
        Disposable disposable = movieDao.insertMovie(movie)
                .subscribeOn(Schedulers.io())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("daut", throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }
public void removeMovie(int movieId){
        Disposable disposable = movieDao.removeMovie(movieId)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    public void loadReview(int id){
        Disposable disposable = ApiFactory.apiService.loadReview(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ReviewAnswerServer, List<Review>>() {
                    @Override
                    public List<Review> apply(ReviewAnswerServer reviewAnswerServer) throws Throwable {
//                        Log.d("daut",reviewAnswerServer.toString());
                        return reviewAnswerServer.getReviewList();
                    }
                })
                .subscribe(new Consumer<List<Review>>() {
                    @Override
                    public void accept(List<Review> reviews) throws Throwable {

                        reviewOfMovie.setValue(reviews);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("daut","We have a problem: "+throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }




    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
