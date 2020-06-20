package com.arbaelbarca.listmovieskatalog.network;

import com.arbaelbarca.listmovieskatalog.model.modeldetailmovies.ResponseDetailMovies;
import com.arbaelbarca.listmovieskatalog.model.modelreviewmovies.ResponseReviewMovies;
import com.arbaelbarca.listmovieskatalog.model.movies.ResponseMoviesList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiServices {

    @GET("movie/{type}")
    Observable<ResponseMoviesList> getMovies(
            @Path("type") String type,
            @Query("language") String language
    );


    @GET("movie/{id_movie}")
    Observable<ResponseDetailMovies> getDetailMovies(
            @Path("id_movie") String type,
            @Query("language") String language
    );

    @GET("movie/{id_movie}/reviews")
    Observable<ResponseReviewMovies> getDetailReviews(
            @Path("id_movie") String type,
            @Query("language") String language
    );
}
