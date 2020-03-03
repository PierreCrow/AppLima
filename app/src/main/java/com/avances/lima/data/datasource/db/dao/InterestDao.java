package com.avances.lima.data.datasource.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.avances.lima.data.datasource.db.model.DbInterest;

import java.util.List;

@Dao
public interface InterestDao {

    @Insert
    void InsertOnlyOne(DbInterest dbInterest);

    @Insert
    void InsertMultiple(List<DbInterest> dbInterests);

    @Update
    void Update(DbInterest dbInterest);

    @Query("SELECT * FROM DbInterest")
    List<DbInterest> GetAll();

    @Delete
    void Delete(DbInterest dbInterest);

    @Query("DELETE  FROM DbInterest")
    void DeleteAll();

}
