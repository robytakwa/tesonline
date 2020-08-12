package com.robytakwa.listpicturekatalog.model.modelreviewmovies

import com.google.gson.annotations.SerializedName

class ResultsItemReviews {

    @SerializedName("author")
    var author: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("content")
    var content: String? = null

    @SerializedName("url")
    var url: String? = null

    override fun toString(): String {
        return "ResultsItemReviews{" +
                "author = '" + author + '\''.toString() +
                ",id = '" + id + '\''.toString() +
                ",content = '" + content + '\''.toString() +
                ",url = '" + url + '\''.toString() +
                "}"
    }
}