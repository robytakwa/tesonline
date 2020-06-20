package com.arbaelbarca.listmovieskatalog.database.room;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.arbaelbarca.listmovieskatalog.model.movies.ResultsItem;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Query("SELECT * FROM favoritemoviesnew")
    Cursor selectAll();

    @Query("SELECT * FROM favoritemoviesnew where id = :id")
    Cursor selectById(long id);

    @Insert
    public void addData(ResultsItem favoriteList);

    @Query("select * from favoritemoviesnew")
    public List<ResultsItem> getFavoriteData();

    @Query("SELECT EXISTS (SELECT 1 FROM favoritemoviesnew WHERE id=:id)")
    public int isFavorite(int id);

    @Delete
    public void delete(ResultsItem favoriteList);

}
