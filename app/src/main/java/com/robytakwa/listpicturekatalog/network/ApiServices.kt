package com.robytakwa.listpicturekatalog.network

import com.robytakwa.listpicturekatalog.model.modeldetailmovies.ResponseDetailMovies
import com.robytakwa.listpicturekatalog.model.modelreviewmovies.ResponseReviewMovies
import com.robytakwa.listpicturekatalog.model.movies.ResponseMoviesList

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface ApiServices {

    @GET("movie/{type}")
    fun getMovies(
            @Path("type") type: String,
            @Query("language") language: String
    ): Observable<ResponseMoviesList>


    @GET("movie/{id_movie}")
    fun getDetailMovies(
            @Path("id_movie") type: String,
            @Query("language") language: String
    ): Observable<ResponseDetailMovies>

    @GET("movie/{id_movie}/reviews")
    fun getDetailReviews(
            @Path("id_movie") type: String,
            @Query("language") language: String
    ): Observable<ResponseReviewMovies>
}
