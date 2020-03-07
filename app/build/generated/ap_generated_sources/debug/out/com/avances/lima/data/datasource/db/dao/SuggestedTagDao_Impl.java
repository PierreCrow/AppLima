package com.avances.lima.data.datasource.db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.avances.lima.data.datasource.db.model.DbSuggestedTag;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class SuggestedTagDao_Impl implements SuggestedTagDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfDbSuggestedTag;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfDbSuggestedTag;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfDbSuggestedTag;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public SuggestedTagDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDbSuggestedTag = new EntityInsertionAdapter<DbSuggestedTag>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `DbSuggestedTag`(`id`,`name`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbSuggestedTag value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
      }
    };
    this.__deletionAdapterOfDbSuggestedTag = new EntityDeletionOrUpdateAdapter<DbSuggestedTag>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `DbSuggestedTag` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbSuggestedTag value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfDbSuggestedTag = new EntityDeletionOrUpdateAdapter<DbSuggestedTag>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `DbSuggestedTag` SET `id` = ?,`name` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbSuggestedTag value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE  FROM DbSuggestedTag";
        return _query;
      }
    };
  }

  @Override
  public void InsertOnlyOne(DbSuggestedTag dbSuggestedTag) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbSuggestedTag.insert(dbSuggestedTag);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void InsertMultiple(List<DbSuggestedTag> dbSuggestedTags) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbSuggestedTag.insert(dbSuggestedTags);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Delete(DbSuggestedTag dbSuggestedTag) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfDbSuggestedTag.handle(dbSuggestedTag);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Update(DbSuggestedTag dbSuggestedTag) {
    __db.beginTransaction();
    try {
      __updateAdapterOfDbSuggestedTag.handle(dbSuggestedTag);
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
  public List<DbSuggestedTag> GetAll() {
    final String _sql = "SELECT * FROM DbSuggestedTag";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final List<DbSuggestedTag> _result = new ArrayList<DbSuggestedTag>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DbSuggestedTag _item;
        _item = new DbSuggestedTag();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
