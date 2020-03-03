package com.avances.lima.data.datasource.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.avances.lima.data.datasource.db.model.DbSuggestedTag;

import java.util.List;

@Dao
public interface SuggestedTagDao {

    @Insert
    void InsertOnlyOne(DbSuggestedTag dbSuggestedTag);

    @Insert
    void InsertMultiple(List<DbSuggestedTag> dbSuggestedTags);

    @Update
    void Update(DbSuggestedTag dbSuggestedTag);

    @Query("SELECT * FROM DbSuggestedTag")
    List<DbSuggestedTag> GetAll();

    @Delete
    void Delete(DbSuggestedTag dbSuggestedTag);

    @Query("DELETE  FROM DbSuggestedTag")
    void DeleteAll();

}
