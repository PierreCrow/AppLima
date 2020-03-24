package com.avances.lima.data.datasource.db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.avances.lima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.lima.presentation.utils.Converters;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class DistritNeighborhoodDao_Impl implements DistritNeighborhoodDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfDbDistritNeighborhood;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfDbDistritNeighborhood;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfDbDistritNeighborhood;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public DistritNeighborhoodDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDbDistritNeighborhood = new EntityInsertionAdapter<DbDistritNeighborhood>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `DbDistritNeighborhood`(`id`,`idCloud`,`idDistritType`,`distrit`,`shortDescription`,`completeDescription`,`imageList`,`active`,`idPlaceList`,`latitude`,`longitude`,`urlVideo`,`tags`,`tagList`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbDistritNeighborhood value) {
        stmt.bindLong(1, value.getId());
        if (value.getIdCloud() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getIdCloud());
        }
        if (value.getIdDistritType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getIdDistritType());
        }
        if (value.getDistrit() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDistrit());
        }
        if (value.getShortDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getShortDescription());
        }
        if (value.getCompleteDescription() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCompleteDescription());
        }
        final String _tmp;
        _tmp = Converters.someObjectListToString(value.getImageList());
        if (_tmp == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, _tmp);
        }
        final int _tmp_1;
        _tmp_1 = value.isActive() ? 1 : 0;
        stmt.bindLong(8, _tmp_1);
        final String _tmp_2;
        _tmp_2 = Converters.someObjectListToString(value.getIdPlaceList());
        if (_tmp_2 == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, _tmp_2);
        }
        if (value.getLatitude() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getLatitude());
        }
        if (value.getLongitude() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getLongitude());
        }
        if (value.getUrlVideo() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getUrlVideo());
        }
        if (value.getTags() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getTags());
        }
        final String _tmp_3;
        _tmp_3 = Converters.someObjectListToString(value.getTagList());
        if (_tmp_3 == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, _tmp_3);
        }
      }
    };
    this.__deletionAdapterOfDbDistritNeighborhood = new EntityDeletionOrUpdateAdapter<DbDistritNeighborhood>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `DbDistritNeighborhood` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbDistritNeighborhood value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfDbDistritNeighborhood = new EntityDeletionOrUpdateAdapter<DbDistritNeighborhood>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `DbDistritNeighborhood` SET `id` = ?,`idCloud` = ?,`idDistritType` = ?,`distrit` = ?,`shortDescription` = ?,`completeDescription` = ?,`imageList` = ?,`active` = ?,`idPlaceList` = ?,`latitude` = ?,`longitude` = ?,`urlVideo` = ?,`tags` = ?,`tagList` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbDistritNeighborhood value) {
        stmt.bindLong(1, value.getId());
        if (value.getIdCloud() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getIdCloud());
        }
        if (value.getIdDistritType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getIdDistritType());
        }
        if (value.getDistrit() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDistrit());
        }
        if (value.getShortDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getShortDescription());
        }
        if (value.getCompleteDescription() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCompleteDescription());
        }
        final String _tmp;
        _tmp = Converters.someObjectListToString(value.getImageList());
        if (_tmp == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, _tmp);
        }
        final int _tmp_1;
        _tmp_1 = value.isActive() ? 1 : 0;
        stmt.bindLong(8, _tmp_1);
        final String _tmp_2;
        _tmp_2 = Converters.someObjectListToString(value.getIdPlaceList());
        if (_tmp_2 == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, _tmp_2);
        }
        if (value.getLatitude() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getLatitude());
        }
        if (value.getLongitude() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getLongitude());
        }
        if (value.getUrlVideo() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getUrlVideo());
        }
        if (value.getTags() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getTags());
        }
        final String _tmp_3;
        _tmp_3 = Converters.someObjectListToString(value.getTagList());
        if (_tmp_3 == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, _tmp_3);
        }
        stmt.bindLong(15, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE  FROM DbDistritNeighborhood";
        return _query;
      }
    };
  }

  @Override
  public void InsertOnlyOne(DbDistritNeighborhood dbDistritNeighborhood) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbDistritNeighborhood.insert(dbDistritNeighborhood);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void InsertMultiple(List<DbDistritNeighborhood> dbDistritNeighborhoods) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbDistritNeighborhood.insert(dbDistritNeighborhoods);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Delete(DbDistritNeighborhood dbDistritNeighborhood) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfDbDistritNeighborhood.handle(dbDistritNeighborhood);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Update(DbDistritNeighborhood dbDistritNeighborhood) {
    __db.beginTransaction();
    try {
      __updateAdapterOfDbDistritNeighborhood.handle(dbDistritNeighborhood);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void DeleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<DbDistritNeighborhood> GetAll() {
    final String _sql = "SELECT * FROM DbDistritNeighborhood";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfIdCloud = _cursor.getColumnIndexOrThrow("idCloud");
      final int _cursorIndexOfIdDistritType = _cursor.getColumnIndexOrThrow("idDistritType");
      final int _cursorIndexOfDistrit = _cursor.getColumnIndexOrThrow("distrit");
      final int _cursorIndexOfShortDescription = _cursor.getColumnIndexOrThrow("shortDescription");
      final int _cursorIndexOfCompleteDescription = _cursor.getColumnIndexOrThrow("completeDescription");
      final int _cursorIndexOfImageList = _cursor.getColumnIndexOrThrow("imageList");
      final int _cursorIndexOfActive = _cursor.getColumnIndexOrThrow("active");
      final int _cursorIndexOfIdPlaceList = _cursor.getColumnIndexOrThrow("idPlaceList");
      final int _cursorIndexOfLatitude = _cursor.getColumnIndexOrThrow("latitude");
      final int _cursorIndexOfLongitude = _cursor.getColumnIndexOrThrow("longitude");
      final int _cursorIndexOfUrlVideo = _cursor.getColumnIndexOrThrow("urlVideo");
      final int _cursorIndexOfTags = _cursor.getColumnIndexOrThrow("tags");
      final int _cursorIndexOfTagList = _cursor.getColumnIndexOrThrow("tagList");
      final List<DbDistritNeighborhood> _result = new ArrayList<DbDistritNeighborhood>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DbDistritNeighborhood _item;
        final String _tmpIdCloud;
        _tmpIdCloud = _cursor.getString(_cursorIndexOfIdCloud);
        final String _tmpIdDistritType;
        _tmpIdDistritType = _cursor.getString(_cursorIndexOfIdDistritType);
        final String _tmpDistrit;
        _tmpDistrit = _cursor.getString(_cursorIndexOfDistrit);
        final String _tmpShortDescription;
        _tmpShortDescription = _cursor.getString(_cursorIndexOfShortDescription);
        final String _tmpCompleteDescription;
        _tmpCompleteDescription = _cursor.getString(_cursorIndexOfCompleteDescription);
        final List<String> _tmpImageList;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfImageList);
        _tmpImageList = Converters.stringToSomeObjectList(_tmp);
        final boolean _tmpActive;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfActive);
        _tmpActive = _tmp_1 != 0;
        final List<String> _tmpIdPlaceList;
        final String _tmp_2;
        _tmp_2 = _cursor.getString(_cursorIndexOfIdPlaceList);
        _tmpIdPlaceList = Converters.stringToSomeObjectList(_tmp_2);
        final String _tmpLatitude;
        _tmpLatitude = _cursor.getString(_cursorIndexOfLatitude);
        final String _tmpLongitude;
        _tmpLongitude = _cursor.getString(_cursorIndexOfLongitude);
        final String _tmpUrlVideo;
        _tmpUrlVideo = _cursor.getString(_cursorIndexOfUrlVideo);
        final String _tmpTags;
        _tmpTags = _cursor.getString(_cursorIndexOfTags);
        final List<String> _tmpTagList;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfTagList);
        _tmpTagList = Converters.stringToSomeObjectList(_tmp_3);
        _item = new DbDistritNeighborhood(_tmpIdCloud,_tmpIdDistritType,_tmpDistrit,_tmpShortDescription,_tmpCompleteDescription,_tmpImageList,_tmpActive,_tmpIdPlaceList,_tmpLatitude,_tmpLongitude,_tmpUrlVideo,_tmpTags,_tmpTagList);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
