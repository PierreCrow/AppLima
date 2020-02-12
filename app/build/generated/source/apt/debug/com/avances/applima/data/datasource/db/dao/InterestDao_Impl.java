package com.avances.applima.data.datasource.db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.avances.applima.data.datasource.db.model.DbInterest;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class InterestDao_Impl implements InterestDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfDbInterest;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfDbInterest;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfDbInterest;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public InterestDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDbInterest = new EntityInsertionAdapter<DbInterest>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `DbInterest`(`id`,`idCloud`,`nameParameterValue`,`detailParameterValue`,`active`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbInterest value) {
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
    this.__deletionAdapterOfDbInterest = new EntityDeletionOrUpdateAdapter<DbInterest>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `DbInterest` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbInterest value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfDbInterest = new EntityDeletionOrUpdateAdapter<DbInterest>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `DbInterest` SET `id` = ?,`idCloud` = ?,`nameParameterValue` = ?,`detailParameterValue` = ?,`active` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbInterest value) {
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
        final String _query = "DELETE  FROM DbInterest";
        return _query;
      }
    };
  }

  @Override
  public void InsertOnlyOne(DbInterest dbInterest) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbInterest.insert(dbInterest);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void InsertMultiple(List<DbInterest> dbInterests) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbInterest.insert(dbInterests);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Delete(DbInterest dbInterest) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfDbInterest.handle(dbInterest);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Update(DbInterest dbInterest) {
    __db.beginTransaction();
    try {
      __updateAdapterOfDbInterest.handle(dbInterest);
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
  public List<DbInterest> GetAll() {
    final String _sql = "SELECT * FROM DbInterest";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfIdCloud = _cursor.getColumnIndexOrThrow("idCloud");
      final int _cursorIndexOfNameParameterValue = _cursor.getColumnIndexOrThrow("nameParameterValue");
      final int _cursorIndexOfDetailParameterValue = _cursor.getColumnIndexOrThrow("detailParameterValue");
      final int _cursorIndexOfActive = _cursor.getColumnIndexOrThrow("active");
      final List<DbInterest> _result = new ArrayList<DbInterest>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DbInterest _item;
        _item = new DbInterest();
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
