package com.avances.lima.data.datasource.db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.avances.lima.data.datasource.db.model.DbEvent;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class EventDao_Impl implements EventDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfDbEvent;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfDbEvent;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfDbEvent;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public EventDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDbEvent = new EntityInsertionAdapter<DbEvent>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `DbEvent`(`id`,`idCloud`,`tittle`,`shortDecription`,`description`,`eventDate`,`eventTime`,`idEventType`,`active`,`isDeleted`,`image`,`startDate`,`finalDate`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbEvent value) {
        stmt.bindLong(1, value.getId());
        if (value.getIdCloud() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getIdCloud());
        }
        if (value.getTittle() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTittle());
        }
        if (value.getShortDecription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getShortDecription());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDescription());
        }
        if (value.getEventDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEventDate());
        }
        if (value.getEventTime() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getEventTime());
        }
        if (value.getIdEventType() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getIdEventType());
        }
        final int _tmp;
        _tmp = value.isActive() ? 1 : 0;
        stmt.bindLong(9, _tmp);
        final int _tmp_1;
        _tmp_1 = value.isDeleted() ? 1 : 0;
        stmt.bindLong(10, _tmp_1);
        if (value.getImage() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getImage());
        }
        if (value.getStartDate() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getStartDate());
        }
        if (value.getFinalDate() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getFinalDate());
        }
      }
    };
    this.__deletionAdapterOfDbEvent = new EntityDeletionOrUpdateAdapter<DbEvent>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `DbEvent` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbEvent value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfDbEvent = new EntityDeletionOrUpdateAdapter<DbEvent>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `DbEvent` SET `id` = ?,`idCloud` = ?,`tittle` = ?,`shortDecription` = ?,`description` = ?,`eventDate` = ?,`eventTime` = ?,`idEventType` = ?,`active` = ?,`isDeleted` = ?,`image` = ?,`startDate` = ?,`finalDate` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbEvent value) {
        stmt.bindLong(1, value.getId());
        if (value.getIdCloud() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getIdCloud());
        }
        if (value.getTittle() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTittle());
        }
        if (value.getShortDecription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getShortDecription());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDescription());
        }
        if (value.getEventDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEventDate());
        }
        if (value.getEventTime() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getEventTime());
        }
        if (value.getIdEventType() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getIdEventType());
        }
        final int _tmp;
        _tmp = value.isActive() ? 1 : 0;
        stmt.bindLong(9, _tmp);
        final int _tmp_1;
        _tmp_1 = value.isDeleted() ? 1 : 0;
        stmt.bindLong(10, _tmp_1);
        if (value.getImage() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getImage());
        }
        if (value.getStartDate() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getStartDate());
        }
        if (value.getFinalDate() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getFinalDate());
        }
        stmt.bindLong(14, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE  FROM DbEvent";
        return _query;
      }
    };
  }

  @Override
  public void InsertOnlyOne(DbEvent dbEvent) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbEvent.insert(dbEvent);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void InsertMultiple(List<DbEvent> dbEvents) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbEvent.insert(dbEvents);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Delete(DbEvent dbEvent) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfDbEvent.handle(dbEvent);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Update(DbEvent dbEvent) {
    __db.beginTransaction();
    try {
      __updateAdapterOfDbEvent.handle(dbEvent);
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
  public List<DbEvent> GetAll() {
    final String _sql = "SELECT * FROM DbEvent";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfIdCloud = _cursor.getColumnIndexOrThrow("idCloud");
      final int _cursorIndexOfTittle = _cursor.getColumnIndexOrThrow("tittle");
      final int _cursorIndexOfShortDecription = _cursor.getColumnIndexOrThrow("shortDecription");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final int _cursorIndexOfEventDate = _cursor.getColumnIndexOrThrow("eventDate");
      final int _cursorIndexOfEventTime = _cursor.getColumnIndexOrThrow("eventTime");
      final int _cursorIndexOfIdEventType = _cursor.getColumnIndexOrThrow("idEventType");
      final int _cursorIndexOfActive = _cursor.getColumnIndexOrThrow("active");
      final int _cursorIndexOfIsDeleted = _cursor.getColumnIndexOrThrow("isDeleted");
      final int _cursorIndexOfImage = _cursor.getColumnIndexOrThrow("image");
      final int _cursorIndexOfStartDate = _cursor.getColumnIndexOrThrow("startDate");
      final int _cursorIndexOfFinalDate = _cursor.getColumnIndexOrThrow("finalDate");
      final List<DbEvent> _result = new ArrayList<DbEvent>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DbEvent _item;
        _item = new DbEvent();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpIdCloud;
        _tmpIdCloud = _cursor.getString(_cursorIndexOfIdCloud);
        _item.setIdCloud(_tmpIdCloud);
        final String _tmpTittle;
        _tmpTittle = _cursor.getString(_cursorIndexOfTittle);
        _item.setTittle(_tmpTittle);
        final String _tmpShortDecription;
        _tmpShortDecription = _cursor.getString(_cursorIndexOfShortDecription);
        _item.setShortDecription(_tmpShortDecription);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _item.setDescription(_tmpDescription);
        final String _tmpEventDate;
        _tmpEventDate = _cursor.getString(_cursorIndexOfEventDate);
        _item.setEventDate(_tmpEventDate);
        final String _tmpEventTime;
        _tmpEventTime = _cursor.getString(_cursorIndexOfEventTime);
        _item.setEventTime(_tmpEventTime);
        final String _tmpIdEventType;
        _tmpIdEventType = _cursor.getString(_cursorIndexOfIdEventType);
        _item.setIdEventType(_tmpIdEventType);
        final boolean _tmpActive;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfActive);
        _tmpActive = _tmp != 0;
        _item.setActive(_tmpActive);
        final boolean _tmpIsDeleted;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsDeleted);
        _tmpIsDeleted = _tmp_1 != 0;
        _item.setDeleted(_tmpIsDeleted);
        final String _tmpImage;
        _tmpImage = _cursor.getString(_cursorIndexOfImage);
        _item.setImage(_tmpImage);
        final String _tmpStartDate;
        _tmpStartDate = _cursor.getString(_cursorIndexOfStartDate);
        _item.setStartDate(_tmpStartDate);
        final String _tmpFinalDate;
        _tmpFinalDate = _cursor.getString(_cursorIndexOfFinalDate);
        _item.setFinalDate(_tmpFinalDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
