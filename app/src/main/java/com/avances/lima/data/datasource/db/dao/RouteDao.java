package com.avances.lima.data.datasource.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.avances.lima.data.datasource.db.model.DbRoute;

import java.util.List;

@Dao
public interface RouteDao {

    @Insert
    void InsertOnlyOne(DbRoute dbRoute);

    @Insert
    void InsertMultiple(List<DbRoute> dbRoutes);

    @Update
    void Update(DbRoute dbRoute);

    @Query("SELECT * FROM DbRoute")
    List<DbRoute> GetAll();

    @Delete
    void Delete(DbRoute dbRoute);

    @Query("DELETE  FROM DbRoute")
    void DeleteAll();

}
