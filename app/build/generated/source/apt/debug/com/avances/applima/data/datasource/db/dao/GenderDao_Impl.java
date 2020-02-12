package com.avances.applima.data.datasource.db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.avances.applima.data.datasource.db.model.DbGender;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class GenderDao_Impl implements GenderDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfDbGender;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfDbGender;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfDbGender;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public GenderDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDbGender = new EntityInsertionAdapter<DbGender>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `DbGender`(`id`,`idCloud`,`nameParameterValue`,`detailParameterValue`,`active`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbGender value) {
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
    this.__deletionAdapterOfDbGender = new EntityDeletionOrUpdateAdapter<DbGender>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `DbGender` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbGender value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfDbGender = new EntityDeletionOrUpdateAdapter<DbGender>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `DbGender` SET `id` = ?,`idCloud` = ?,`nameParameterValue` = ?,`detailParameterValue` = ?,`active` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbGender value) {
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
        final String _query = "DELETE  FROM DbGender";
        return _query;
      }
    };
  }

  @Override
  public void InsertOnlyOne(DbGender dbGender) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbGender.insert(dbGender);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void InsertMultiple(List<DbGender> dbGenders) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbGender.insert(dbGenders);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Delete(DbGender dbGender) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfDbGender.handle(dbGender);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Update(DbGender dbGender) {
    __db.beginTransaction();
    try {
      __updateAdapterOfDbGender.handle(dbGender);
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
  public List<DbGender> GetAll() {
    final String _sql = "SELECT * FROM DbGender";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfIdCloud = _cursor.getColumnIndexOrThrow("idCloud");
      final int _cursorIndexOfNameParameterValue = _cursor.getColumnIndexOrThrow("nameParameterValue");
      final int _cursorIndexOfDetailParameterValue = _cursor.getColumnIndexOrThrow("detailParameterValue");
      final int _cursorIndexOfActive = _cursor.getColumnIndexOrThrow("active");
      final List<DbGender> _result = new ArrayList<DbGender>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DbGender _item;
        _item = new DbGender();
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
