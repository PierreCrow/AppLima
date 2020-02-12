package com.avances.applima.data.datasource.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.avances.applima.data.datasource.db.model.DbEvent;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    void InsertOnlyOne(DbEvent dbEvent);

    @Insert
    void InsertMultiple(List<DbEvent> dbEvents);

    @Update
    void Update(DbEvent dbEvent);

    @Query("SELECT * FROM DbEvent")
    List<DbEvent> GetAll();

    @Delete
    void Delete(DbEvent dbEvent);

    @Query("DELETE  FROM DbEvent")
    void DeleteAll();

   // @Query("SELECT id, ( 6371 * acos( cos( radians(28) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(-116) ) + sin( radians(28) ) * sin( radians( lat ) ) ) ) AS distance FROM markers HAVING distance < 25 ORDER BY distance LIMIT 0 , 20")
   // void nose();

}
