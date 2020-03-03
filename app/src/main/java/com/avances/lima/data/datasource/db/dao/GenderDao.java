package com.avances.lima.data.datasource.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.avances.lima.data.datasource.db.model.DbGender;

import java.util.List;

@Dao
public interface GenderDao {

    @Insert
    void InsertOnlyOne(DbGender dbGender);

    @Insert
    void InsertMultiple(List<DbGender> dbGenders);

    @Update
    void Update(DbGender dbGender);

    @Query("SELECT * FROM DbGender")
    List<DbGender> GetAll();

    @Delete
    void Delete(DbGender dbGender);

    @Query("DELETE  FROM DbGender")
    void DeleteAll();

}
