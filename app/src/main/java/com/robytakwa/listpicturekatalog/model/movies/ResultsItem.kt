package com.robytakwa.listpicturekatalog.model.movies

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favoritemoviesnew")
class ResultsItem : Parcelable {

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    var overview: String? = null

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    var originalLanguage: String? = null

    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    var originalTitle: String? = null

    @ColumnInfo(name = "video")
    @SerializedName("video")
    var isVideo: Boolean = false

    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String? = null

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    var posterPath: String? = null

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    var releaseDate: String? = null

    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    var popularity: Double = 0.toDouble()

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    var voteAverage: Double = 0.toDouble()

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0

    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    var isAdult: Boolean = false

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    var voteCount: Int = 0

    constructor() {

    }

    constructor(`in`: Parcel) {
        overview = `in`.readString()
        originalLanguage = `in`.readString()
        originalTitle = `in`.readString()
        isVideo = `in`.readByte().toInt() != 0
        title = `in`.readString()
        posterPath = `in`.readString()
        backdropPath = `in`.readString()
        releaseDate = `in`.readString()
        popularity = `in`.readDouble()
        voteAverage = `in`.readDouble()
        id = `in`.readInt()
        isAdult = `in`.readByte().toInt() != 0
        voteCount = `in`.readInt()
    }

    override fun toString(): String {
        return "ResultsItemReviews{" +
                "overview = '" + overview + '\''.toString() +
                ",original_language = '" + originalLanguage + '\''.toString() +
                ",original_title = '" + originalTitle + '\''.toString() +
                ",video = '" + isVideo + '\''.toString() +
                ",title = '" + title + '\''.toString() +
                //                        ",genre_ids = '" + genreIds + '\'' +
                ",poster_path = '" + posterPath + '\''.toString() +
                ",backdrop_path = '" + backdropPath + '\''.toString() +
                ",release_date = '" + releaseDate + '\''.toString() +
                ",popularity = '" + popularity + '\''.toString() +
                ",vote_average = '" + voteAverage + '\''.toString() +
                ",id = '" + id + '\''.toString() +
                ",adult = '" + isAdult + '\''.toString() +
                ",vote_count = '" + voteCount + '\''.toString() +
                "}"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(overview)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalTitle)
        parcel.writeByte((if (isVideo) 1 else 0).toByte())
        parcel.writeString(title)
        parcel.writeString(posterPath)
        parcel.writeString(backdropPath)
        parcel.writeString(releaseDate)
        parcel.writeDouble(popularity)
        parcel.writeDouble(voteAverage)
        parcel.writeInt(id)
        parcel.writeByte((if (isAdult) 1 else 0).toByte())
        parcel.writeInt(voteCount)
    }



    companion object CREATOR : Parcelable.Creator<ResultsItem> {
        override fun createFromParcel(parcel: Parcel): ResultsItem {
            return ResultsItem(parcel)
        }

        override fun newArray(size: Int): Array<ResultsItem?> {
            return arrayOfNulls(size)
        }
    }


}