package com.robytakwa.listpicturekatalog.model.movies

import com.google.gson.annotations.SerializedName
import java.util.*

class ResponseMoviesList {

    @SerializedName("page")
    var page: Int = 0

    @SerializedName("total_pages")
    var totalPages: Int = 0

    @SerializedName("results")
    var results: ArrayList<ResultsItem>? = null

    @SerializedName("total_results")
    var totalResults: Int = 0

    override fun toString(): String {
        return "ResponseMoviesList{" +
                "page = '" + page + '\''.toString() +
                ",total_pages = '" + totalPages + '\''.toString() +
                ",results = '" + results + '\''.toString() +
                ",total_results = '" + totalResults + '\''.toString() +
                "}"
    }
}