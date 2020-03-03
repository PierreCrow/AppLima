package com.avances.lima.data.datasource.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.avances.lima.data.datasource.db.model.DbPlace;

import java.util.List;

@Dao
public interface PlaceDao {

    @Insert
    void InsertOnlyOne(DbPlace dbPlace);

    @Insert
    void InsertMultiple(List<DbPlace> dbPlaces);

    @Update
    void Update(DbPlace dbPlace);

    @Query("SELECT * FROM DbPlace")
    List<DbPlace> GetAll();

    @Delete
    void Delete(DbPlace dbPlace);

    @Query("DELETE  FROM DbPlace")
    void DeleteAll();

    @Query("UPDATE DbPlace set favorite=:fav WHERE idCloud=:idPlace")
    void setFavorite(boolean fav,String idPlace);

}
