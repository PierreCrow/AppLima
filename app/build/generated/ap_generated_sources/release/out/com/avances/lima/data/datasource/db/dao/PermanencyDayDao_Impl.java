package com.avances.lima.data.datasource.db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.avances.lima.data.datasource.db.model.DbPermanencyDay;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class PermanencyDayDao_Impl implements PermanencyDayDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfDbPermanencyDay;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfDbPermanencyDay;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfDbPermanencyDay;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public PermanencyDayDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDbPermanencyDay = new EntityInsertionAdapter<DbPermanencyDay>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `DbPermanencyDay`(`id`,`idCloud`,`nameParameterValue`,`detailParameterValue`,`active`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbPermanencyDay value) {
        stmt.bindLong(1, value.getId());
        if (value.getIdCloud() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getIdCloud());
        }
        if (value.getNameParameterValue() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getNameParameterValue());
        }
        if (value.getDetailParameterValue() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDetailParameterValue());
        }
        final int _tmp;
        _tmp = value.isActive() ? 1 : 0;
        stmt.bindLong(5, _tmp);
      }
    };
    this.__deletionAdapterOfDbPermanencyDay = new EntityDeletionOrUpdateAdapter<DbPermanencyDay>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `DbPermanencyDay` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbPermanencyDay value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfDbPermanencyDay = new EntityDeletionOrUpdateAdapter<DbPermanencyDay>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `DbPermanencyDay` SET `id` = ?,`idCloud` = ?,`nameParameterValue` = ?,`detailParameterValue` = ?,`active` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbPermanencyDay value) {
        stmt.bindLong(1, value.getId());
        if (value.getIdCloud() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getIdCloud());
        }
        if (value.getNameParameterValue() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getNameParameterValue());
        }
        if (value.getDetailParameterValue() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDetailParameterValue());
        }
        final int _tmp;
        _tmp = value.isActive() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        stmt.bindLong(6, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE  FROM DbPermanencyDay";
        return _query;
      }
    };
  }

  @Override
  public void InsertOnlyOne(DbPermanencyDay dbPermanencyDay) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbPermanencyDay.insert(dbPermanencyDay);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void InsertMultiple(List<DbPermanencyDay> dbPermanencyDays) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbPermanencyDay.insert(dbPermanencyDays);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Delete(DbPermanencyDay dbPermanencyDay) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfDbPermanencyDay.handle(dbPermanencyDay);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Update(DbPermanencyDay dbPermanencyDay) {
    __db.beginTransaction();
    try {
      __updateAdapterOfDbPermanencyDay.handle(dbPermanencyDay);
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
  public List<DbPermanencyDay> GetAll() {
    final String _sql = "SELECT * FROM DbPermanencyDay";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfIdCloud = _cursor.getColumnIndexOrThrow("idCloud");
      final int _cursorIndexOfNameParameterValue = _cursor.getColumnIndexOrThrow("nameParameterValue");
      final int _cursorIndexOfDetailParameterValue = _cursor.getColumnIndexOrThrow("detailParameterValue");
      final int _cursorIndexOfActive = _cursor.getColumnIndexOrThrow("active");
      final List<DbPermanencyDay> _result = new ArrayList<DbPermanencyDay>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DbPermanencyDay _item;
        _item = new DbPermanencyDay();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpIdCloud;
        _tmpIdCloud = _cursor.getString(_cursorIndexOfIdCloud);
        _item.setIdCloud(_tmpIdCloud);
        final String _tmpNameParameterValue;
        _tmpNameParameterValue = _cursor.getString(_cursorIndexOfNameParameterValue);
        _item.setNameParameterValue(_tmpNameParameterValue);
        final String _tmpDetailParameterValue;
        _tmpDetailParameterValue = _cursor.getString(_cursorIndexOfDetailParameterValue);
        _item.setDetailParameterValue(_tmpDetailParameterValue);
        final boolean _tmpActive;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfActive);
        _tmpActive = _tmp != 0;
        _item.setActive(_tmpActive);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
