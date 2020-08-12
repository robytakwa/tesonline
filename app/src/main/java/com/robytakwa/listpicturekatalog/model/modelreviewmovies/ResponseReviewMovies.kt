package com.robytakwa.listpicturekatalog.model.modelreviewmovies

import com.google.gson.annotations.SerializedName
import java.util.*

class ResponseReviewMovies {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("page")
    var page: Int = 0

    @SerializedName("total_pages")
    var totalPages: Int = 0

    @SerializedName("results")
    var results: ArrayList<ResultsItemReviews>? = null

    @SerializedName("total_results")
    var totalResults: Int = 0

    override fun toString(): String {
        return "ResponseReviewMovies{" +
                "id = '" + id + '\''.toString() +
                ",page = '" + page + '\''.toString() +
                ",total_pages = '" + totalPages + '\''.toString() +
                ",results = '" + results + '\''.toString() +
                ",total_results = '" + totalResults + '\''.toString() +
                "}"
    }
}