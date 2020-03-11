package com.avances.lima.data.datasource.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.avances.lima.data.datasource.db.model.DbPermanencyDay;

import java.util.List;

@Dao
public interface PermanencyDayDao {

    @Insert
    void InsertOnlyOne(DbPermanencyDay dbPermanencyDay);

    @Insert
    void InsertMultiple(List<DbPermanencyDay> dbPermanencyDays);

    @Update
    void Update(DbPermanencyDay dbPermanencyDay);

    @Query("SELECT * FROM DbPermanencyDay")
    List<DbPermanencyDay> GetAll();

    @Delete
    void Delete(DbPermanencyDay dbPermanencyDay);

    @Query("DELETE  FROM DbPermanencyDay")
    void DeleteAll();

}
