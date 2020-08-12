package com.robytakwa.listpicturekatalog.database.room

import android.database.Cursor

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

import com.robytakwa.listpicturekatalog.model.movies.ResultsItem

@Dao
interface FavoriteDao {

    @get:Query("select * from favoritemoviesnew")
    val favoriteData: List<ResultsItem>

    @Query("SELECT * FROM favoritemoviesnew")
    fun selectAll(): Cursor

    @Query("SELECT * FROM favoritemoviesnew where id = :id")
    fun selectById(id: Long): Cursor

    @Insert
    fun addData(favoriteList: ResultsItem)

    @Query("SELECT EXISTS (SELECT 1 FROM favoritemoviesnew WHERE id=:id)")
    fun isFavorite(id: Int): Int

    @Delete
    fun delete(favoriteList: ResultsItem)

}
