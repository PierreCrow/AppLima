package com.avances.applima.data.datasource.db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.avances.applima.data.datasource.db.model.DbRoute;
import com.avances.applima.presentation.utils.Converters;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class RouteDao_Impl implements RouteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfDbRoute;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfDbRoute;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfDbRoute;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public RouteDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDbRoute = new EntityInsertionAdapter<DbRoute>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `DbRoute`(`id`,`idCloud`,`idRouteType`,`routeName`,`idUserRegister`,`idUserModify`,`registerDate`,`modifyDate`,`isDeleted`,`image`,`iconImage`,`idPlaceList`,`tags`,`tagList`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbRoute value) {
        stmt.bindLong(1, value.getId());
        if (value.getIdCloud() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getIdCloud());
        }
        if (value.getIdRouteType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getIdRouteType());
        }
        if (value.getRouteName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getRouteName());
        }
        if (value.getIdUserRegister() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getIdUserRegister());
        }
        if (value.getIdUserModify() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getIdUserModify());
        }
        if (value.getRegisterDate() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getRegisterDate());
        }
        if (value.getModifyDate() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getModifyDate());
        }
        final int _tmp;
        _tmp = value.isDeleted() ? 1 : 0;
        stmt.bindLong(9, _tmp);
        if (value.getImage() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getImage());
        }
        if (value.getIconImage() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getIconImage());
        }
        final String _tmp_1;
        _tmp_1 = Converters.someObjectListToString(value.getIdPlaceList());
        if (_tmp_1 == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, _tmp_1);
        }
        if (value.getTags() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getTags());
        }
        final String _tmp_2;
        _tmp_2 = Converters.someObjectListToString(value.getTagList());
        if (_tmp_2 == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, _tmp_2);
        }
      }
    };
    this.__deletionAdapterOfDbRoute = new EntityDeletionOrUpdateAdapter<DbRoute>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `DbRoute` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbRoute value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfDbRoute = new EntityDeletionOrUpdateAdapter<DbRoute>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `DbRoute` SET `id` = ?,`idCloud` = ?,`idRouteType` = ?,`routeName` = ?,`idUserRegister` = ?,`idUserModify` = ?,`registerDate` = ?,`modifyDate` = ?,`isDeleted` = ?,`image` = ?,`iconImage` = ?,`idPlaceList` = ?,`tags` = ?,`tagList` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbRoute value) {
        stmt.bindLong(1, value.getId());
        if (value.getIdCloud() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getIdCloud());
        }
        if (value.getIdRouteType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getIdRouteType());
        }
        if (value.getRouteName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getRouteName());
        }
        if (value.getIdUserRegister() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getIdUserRegister());
        }
        if (value.getIdUserModify() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getIdUserModify());
        }
        if (value.getRegisterDate() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getRegisterDate());
        }
        if (value.getModifyDate() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getModifyDate());
        }
        final int _tmp;
        _tmp = value.isDeleted() ? 1 : 0;
        stmt.bindLong(9, _tmp);
        if (value.getImage() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getImage());
        }
        if (value.getIconImage() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getIconImage());
        }
        final String _tmp_1;
        _tmp_1 = Converters.someObjectListToString(value.getIdPlaceList());
        if (_tmp_1 == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, _tmp_1);
        }
        if (value.getTags() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getTags());
        }
        final String _tmp_2;
        _tmp_2 = Converters.someObjectListToString(value.getTagList());
        if (_tmp_2 == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, _tmp_2);
        }
        stmt.bindLong(15, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE  FROM DbRoute";
        return _query;
      }
    };
  }

  @Override
  public void InsertOnlyOne(DbRoute dbRoute) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbRoute.insert(dbRoute);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void InsertMultiple(List<DbRoute> dbRoutes) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbRoute.insert(dbRoutes);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Delete(DbRoute dbRoute) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfDbRoute.handle(dbRoute);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Update(DbRoute dbRoute) {
    __db.beginTransaction();
    try {
      __updateAdapterOfDbRoute.handle(dbRoute);
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
  public List<DbRoute> GetAll() {
    final String _sql = "SELECT * FROM DbRoute";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfIdCloud = _cursor.getColumnIndexOrThrow("idCloud");
      final int _cursorIndexOfIdRouteType = _cursor.getColumnIndexOrThrow("idRouteType");
      final int _cursorIndexOfRouteName = _cursor.getColumnIndexOrThrow("routeName");
      final int _cursorIndexOfIdUserRegister = _cursor.getColumnIndexOrThrow("idUserRegister");
      final int _cursorIndexOfIdUserModify = _cursor.getColumnIndexOrThrow("idUserModify");
      final int _cursorIndexOfRegisterDate = _cursor.getColumnIndexOrThrow("registerDate");
      final int _cursorIndexOfModifyDate = _cursor.getColumnIndexOrThrow("modifyDate");
      final int _cursorIndexOfIsDeleted = _cursor.getColumnIndexOrThrow("isDeleted");
      final int _cursorIndexOfImage = _cursor.getColumnIndexOrThrow("image");
      final int _cursorIndexOfIconImage = _cursor.getColumnIndexOrThrow("iconImage");
      final int _cursorIndexOfIdPlaceList = _cursor.getColumnIndexOrThrow("idPlaceList");
      final int _cursorIndexOfTags = _cursor.getColumnIndexOrThrow("tags");
      final int _cursorIndexOfTagList = _cursor.getColumnIndexOrThrow("tagList");
      final List<DbRoute> _result = new ArrayList<DbRoute>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DbRoute _item;
        final String _tmpIdCloud;
        _tmpIdCloud = _cursor.getString(_cursorIndexOfIdCloud);
        final String _tmpIdRouteType;
        _tmpIdRouteType = _cursor.getString(_cursorIndexOfIdRouteType);
        final String _tmpRouteName;
        _tmpRouteName = _cursor.getString(_cursorIndexOfRouteName);
        final String _tmpIdUserRegister;
        _tmpIdUserRegister = _cursor.getString(_cursorIndexOfIdUserRegister);
        final String _tmpIdUserModify;
        _tmpIdUserModify = _cursor.getString(_cursorIndexOfIdUserModify);
        final String _tmpRegisterDate;
        _tmpRegisterDate = _cursor.getString(_cursorIndexOfRegisterDate);
        final String _tmpModifyDate;
        _tmpModifyDate = _cursor.getString(_cursorIndexOfModifyDate);
        final boolean _tmpIsDeleted;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsDeleted);
        _tmpIsDeleted = _tmp != 0;
        final String _tmpImage;
        _tmpImage = _cursor.getString(_cursorIndexOfImage);
        final String _tmpIconImage;
        _tmpIconImage = _cursor.getString(_cursorIndexOfIconImage);
        final List<String> _tmpIdPlaceList;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfIdPlaceList);
        _tmpIdPlaceList = Converters.stringToSomeObjectList(_tmp_1);
        final String _tmpTags;
        _tmpTags = _cursor.getString(_cursorIndexOfTags);
        final List<String> _tmpTagList;
        final String _tmp_2;
        _tmp_2 = _cursor.getString(_cursorIndexOfTagList);
        _tmpTagList = Converters.stringToSomeObjectList(_tmp_2);
        _item = new DbRoute(_tmpIdCloud,_tmpIdRouteType,_tmpRouteName,_tmpIdUserRegister,_tmpIdUserModify,_tmpRegisterDate,_tmpModifyDate,_tmpIsDeleted,_tmpImage,_tmpIconImage,_tmpIdPlaceList,_tmpTags,_tmpTagList);
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
