package com.robytakwa.listpicturekatalog.model.modeldetailmovies

import com.google.gson.annotations.SerializedName
import java.util.*

class ResponseDetailMovies {

    @SerializedName("original_language")
    var originalLanguage: String? = null

    @SerializedName("imdb_id")
    var imdbId: String? = null

    @SerializedName("video")
    var isVideo: Boolean = false

    @SerializedName("title")
    var title: String? = null

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("revenue")
    var revenue: Int = 0

    @SerializedName("genres")
    var genres: ArrayList<GenresItem>? = null

    @SerializedName("popularity")
    var popularity: Double = 0.toDouble()

    @SerializedName("production_countries")
    var productionCountries: List<ProductionCountriesItem>? = null

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("vote_count")
    var voteCount: Int = 0

    @SerializedName("budget")
    var budget: Int = 0

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("original_title")
    var originalTitle: String? = null

    @SerializedName("runtime")
    var runtime: Int = 0

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("spoken_languages")
    var spokenLanguages: List<SpokenLanguagesItem>? = null

    @SerializedName("production_companies")
    var productionCompanies: List<ProductionCompaniesItem>? = null

    @SerializedName("release_date")
    var releaseDate: String? = null

    @SerializedName("vote_average")
    var voteAverage: Double = 0.toDouble()

    @SerializedName("belongs_to_collection")
    var belongsToCollection: Any? = null

    @SerializedName("tagline")
    var tagline: String? = null

    @SerializedName("adult")
    var isAdult: Boolean = false

    @SerializedName("homepage")
    var homepage: String? = null

    @SerializedName("status")
    var status: String? = null

    override fun toString(): String {
        return "ResponseDetailMovies{" +
                "original_language = '" + originalLanguage + '\''.toString() +
                ",imdb_id = '" + imdbId + '\''.toString() +
                ",video = '" + isVideo + '\''.toString() +
                ",title = '" + title + '\''.toString() +
                ",backdrop_path = '" + backdropPath + '\''.toString() +
                ",revenue = '" + revenue + '\''.toString() +
                ",genres = '" + genres + '\''.toString() +
                ",popularity = '" + popularity + '\''.toString() +
                ",production_countries = '" + productionCountries + '\''.toString() +
                ",id = '" + id + '\''.toString() +
                ",vote_count = '" + voteCount + '\''.toString() +
                ",budget = '" + budget + '\''.toString() +
                ",overview = '" + overview + '\''.toString() +
                ",original_title = '" + originalTitle + '\''.toString() +
                ",runtime = '" + runtime + '\''.toString() +
                ",poster_path = '" + posterPath + '\''.toString() +
                ",spoken_languages = '" + spokenLanguages + '\''.toString() +
                ",production_companies = '" + productionCompanies + '\''.toString() +
                ",release_date = '" + releaseDate + '\''.toString() +
                ",vote_average = '" + voteAverage + '\''.toString() +
                ",belongs_to_collection = '" + belongsToCollection + '\''.toString() +
                ",tagline = '" + tagline + '\''.toString() +
                ",adult = '" + isAdult + '\''.toString() +
                ",homepage = '" + homepage + '\''.toString() +
                ",status = '" + status + '\''.toString() +
                "}"
    }
}