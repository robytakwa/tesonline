package com.arbaelbarca.listmovieskatalog.database.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.arbaelbarca.listmovieskatalog.model.movies.ResultsItem;

@Database(entities = {ResultsItem.class}, version = 3, exportSchema = false)
public abstract class FavoriteDatabase extends RoomDatabase {
    public abstract FavoriteDao favoriteDao();
}
