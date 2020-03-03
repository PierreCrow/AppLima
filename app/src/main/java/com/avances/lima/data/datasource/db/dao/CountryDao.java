package com.avances.lima.data.datasource.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.avances.lima.data.datasource.db.model.DbCountry;

import java.util.List;

@Dao
public interface CountryDao {

    @Insert
    void InsertOnlyOne(DbCountry dbCountry);

    @Insert
    void InsertMultiple(List<DbCountry> dbCountrys);

    @Update
    void Update(DbCountry dbCountry);

    @Query("SELECT * FROM DbCountry")
    List<DbCountry> GetAll();

    @Delete
    void Delete(DbCountry dbCountry);

    @Query("DELETE  FROM DbCountry")
    void DeleteAll();

}
