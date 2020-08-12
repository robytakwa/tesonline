package com.robytakwa.listpicturekatalog.model.modeldetailmovies

import com.google.gson.annotations.SerializedName

class ProductionCountriesItem {

    @SerializedName("iso_3166_1")
    var iso31661: String? = null

    @SerializedName("name")
    var name: String? = null

    override fun toString(): String {
        return "ProductionCountriesItem{" +
                "iso_3166_1 = '" + iso31661 + '\''.toString() +
                ",name = '" + name + '\''.toString() +
                "}"
    }
}