package com.avances.lima.data.datasource.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.avances.lima.data.datasource.db.model.DbDistritNeighborhood;

import java.util.List;

@Dao
public interface DistritNeighborhoodDao {

    @Insert
    void InsertOnlyOne(DbDistritNeighborhood dbDistritNeighborhood);

    @Insert
    void InsertMultiple(List<DbDistritNeighborhood> dbDistritNeighborhoods);

    @Update
    void Update(DbDistritNeighborhood dbDistritNeighborhood);

    @Query("SELECT * FROM DbDistritNeighborhood")
    List<DbDistritNeighborhood> GetAll();

    @Delete
    void Delete(DbDistritNeighborhood dbDistritNeighborhood);

    @Query("DELETE  FROM DbDistritNeighborhood")
    void DeleteAll();

}
