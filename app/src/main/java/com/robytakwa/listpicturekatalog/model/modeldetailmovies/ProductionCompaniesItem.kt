package com.robytakwa.listpicturekatalog.model.modeldetailmovies

import com.google.gson.annotations.SerializedName

class ProductionCompaniesItem {

    @SerializedName("logo_path")
    var logoPath: Any? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("origin_country")
    var originCountry: String? = null

    override fun toString(): String {
        return "ProductionCompaniesItem{" +
                "logo_path = '" + logoPath + '\''.toString() +
                ",name = '" + name + '\''.toString() +
                ",id = '" + id + '\''.toString() +
                ",origin_country = '" + originCountry + '\''.toString() +
                "}"
    }
}