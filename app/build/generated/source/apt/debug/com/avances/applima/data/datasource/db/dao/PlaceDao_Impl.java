package com.avances.applima.data.datasource.db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.presentation.utils.Converters;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class PlaceDao_Impl implements PlaceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfDbPlace;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfDbPlace;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfDbPlace;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfSetFavorite;

  public PlaceDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDbPlace = new EntityInsertionAdapter<DbPlace>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `DbPlace`(`id`,`idCloud`,`tittle`,`resume`,`detail`,`address`,`webPage`,`phone`,`idDistritNeighborhood`,`lat`,`lng`,`active`,`isDeleted`,`textTags`,`textTagsList`,`interviewed`,`imageList`,`favorite`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbPlace value) {
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
        if (value.getResume() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getResume());
        }
        if (value.getDetail() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDetail());
        }
        if (value.getAddress() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAddress());
        }
        if (value.getWebPage() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getWebPage());
        }
        if (value.getPhone() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getPhone());
        }
        if (value.getIdDistritNeighborhood() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getIdDistritNeighborhood());
        }
        if (value.getLat() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getLat());
        }
        if (value.getLng() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getLng());
        }
        final int _tmp;
        _tmp = value.isActive() ? 1 : 0;
        stmt.bindLong(12, _tmp);
        final int _tmp_1;
        _tmp_1 = value.isDeleted() ? 1 : 0;
        stmt.bindLong(13, _tmp_1);
        if (value.getTextTags() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getTextTags());
        }
        final String _tmp_2;
        _tmp_2 = Converters.someObjectListToString(value.getTextTagsList());
        if (_tmp_2 == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, _tmp_2);
        }
        final String _tmp_3;
        _tmp_3 = Converters.someObjectListToString(value.getInterviewed());
        if (_tmp_3 == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, _tmp_3);
        }
        final String _tmp_4;
        _tmp_4 = Converters.someObjectListToString(value.getImageList());
        if (_tmp_4 == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, _tmp_4);
        }
        final int _tmp_5;
        _tmp_5 = value.isFavorite() ? 1 : 0;
        stmt.bindLong(18, _tmp_5);
      }
    };
    this.__deletionAdapterOfDbPlace = new EntityDeletionOrUpdateAdapter<DbPlace>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `DbPlace` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbPlace value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfDbPlace = new EntityDeletionOrUpdateAdapter<DbPlace>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `DbPlace` SET `id` = ?,`idCloud` = ?,`tittle` = ?,`resume` = ?,`detail` = ?,`address` = ?,`webPage` = ?,`phone` = ?,`idDistritNeighborhood` = ?,`lat` = ?,`lng` = ?,`active` = ?,`isDeleted` = ?,`textTags` = ?,`textTagsList` = ?,`interviewed` = ?,`imageList` = ?,`favorite` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbPlace value) {
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
        if (value.getResume() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getResume());
        }
        if (value.getDetail() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDetail());
        }
        if (value.getAddress() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAddress());
        }
        if (value.getWebPage() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getWebPage());
        }
        if (value.getPhone() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getPhone());
        }
        if (value.getIdDistritNeighborhood() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getIdDistritNeighborhood());
        }
        if (value.getLat() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getLat());
        }
        if (value.getLng() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getLng());
        }
        final int _tmp;
        _tmp = value.isActive() ? 1 : 0;
        stmt.bindLong(12, _tmp);
        final int _tmp_1;
        _tmp_1 = value.isDeleted() ? 1 : 0;
        stmt.bindLong(13, _tmp_1);
        if (value.getTextTags() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getTextTags());
        }
        final String _tmp_2;
        _tmp_2 = Converters.someObjectListToString(value.getTextTagsList());
        if (_tmp_2 == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, _tmp_2);
        }
        final String _tmp_3;
        _tmp_3 = Converters.someObjectListToString(value.getInterviewed());
        if (_tmp_3 == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, _tmp_3);
        }
        final String _tmp_4;
        _tmp_4 = Converters.someObjectListToString(value.getImageList());
        if (_tmp_4 == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, _tmp_4);
        }
        final int _tmp_5;
        _tmp_5 = value.isFavorite() ? 1 : 0;
        stmt.bindLong(18, _tmp_5);
        stmt.bindLong(19, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE  FROM DbPlace";
        return _query;
      }
    };
    this.__preparedStmtOfSetFavorite = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE DbPlace set favorite=? WHERE idCloud=?";
        return _query;
      }
    };
  }

  @Override
  public void InsertOnlyOne(DbPlace dbPlace) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbPlace.insert(dbPlace);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void InsertMultiple(List<DbPlace> dbPlaces) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbPlace.insert(dbPlaces);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Delete(DbPlace dbPlace) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfDbPlace.handle(dbPlace);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Update(DbPlace dbPlace) {
    __db.beginTransaction();
    try {
      __updateAdapterOfDbPlace.handle(dbPlace);
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
  public void setFavorite(boolean fav, String idPlace) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetFavorite.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      final int _tmp;
      _tmp = fav ? 1 : 0;
      _stmt.bindLong(_argIndex, _tmp);
      _argIndex = 2;
      if (idPlace == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, idPlace);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetFavorite.release(_stmt);
    }
  }

  @Override
  public List<DbPlace> GetAll() {
    final String _sql = "SELECT * FROM DbPlace";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfIdCloud = _cursor.getColumnIndexOrThrow("idCloud");
      final int _cursorIndexOfTittle = _cursor.getColumnIndexOrThrow("tittle");
      final int _cursorIndexOfResume = _cursor.getColumnIndexOrThrow("resume");
      final int _cursorIndexOfDetail = _cursor.getColumnIndexOrThrow("detail");
      final int _cursorIndexOfAddress = _cursor.getColumnIndexOrThrow("address");
      final int _cursorIndexOfWebPage = _cursor.getColumnIndexOrThrow("webPage");
      final int _cursorIndexOfPhone = _cursor.getColumnIndexOrThrow("phone");
      final int _cursorIndexOfIdDistritNeighborhood = _cursor.getColumnIndexOrThrow("idDistritNeighborhood");
      final int _cursorIndexOfLat = _cursor.getColumnIndexOrThrow("lat");
      final int _cursorIndexOfLng = _cursor.getColumnIndexOrThrow("lng");
      final int _cursorIndexOfActive = _cursor.getColumnIndexOrThrow("active");
      final int _cursorIndexOfIsDeleted = _cursor.getColumnIndexOrThrow("isDeleted");
      final int _cursorIndexOfTextTags = _cursor.getColumnIndexOrThrow("textTags");
      final int _cursorIndexOfTextTagsList = _cursor.getColumnIndexOrThrow("textTagsList");
      final int _cursorIndexOfInterviewed = _cursor.getColumnIndexOrThrow("interviewed");
      final int _cursorIndexOfImageList = _cursor.getColumnIndexOrThrow("imageList");
      final int _cursorIndexOfFavorite = _cursor.getColumnIndexOrThrow("favorite");
      final List<DbPlace> _result = new ArrayList<DbPlace>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DbPlace _item;
        final String _tmpIdCloud;
        _tmpIdCloud = _cursor.getString(_cursorIndexOfIdCloud);
        final String _tmpTittle;
        _tmpTittle = _cursor.getString(_cursorIndexOfTittle);
        final String _tmpResume;
        _tmpResume = _cursor.getString(_cursorIndexOfResume);
        final String _tmpDetail;
        _tmpDetail = _cursor.getString(_cursorIndexOfDetail);
        final String _tmpAddress;
        _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
        final String _tmpWebPage;
        _tmpWebPage = _cursor.getString(_cursorIndexOfWebPage);
        final String _tmpPhone;
        _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        final String _tmpIdDistritNeighborhood;
        _tmpIdDistritNeighborhood = _cursor.getString(_cursorIndexOfIdDistritNeighborhood);
        final String _tmpLat;
        _tmpLat = _cursor.getString(_cursorIndexOfLat);
        final String _tmpLng;
        _tmpLng = _cursor.getString(_cursorIndexOfLng);
        final boolean _tmpActive;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfActive);
        _tmpActive = _tmp != 0;
        final boolean _tmpIsDeleted;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsDeleted);
        _tmpIsDeleted = _tmp_1 != 0;
        final String _tmpTextTags;
        _tmpTextTags = _cursor.getString(_cursorIndexOfTextTags);
        final List<String> _tmpTextTagsList;
        final String _tmp_2;
        _tmp_2 = _cursor.getString(_cursorIndexOfTextTagsList);
        _tmpTextTagsList = Converters.stringToSomeObjectList(_tmp_2);
        final List<String> _tmpInterviewed;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfInterviewed);
        _tmpInterviewed = Converters.stringToSomeObjectList(_tmp_3);
        final List<String> _tmpImageList;
        final String _tmp_4;
        _tmp_4 = _cursor.getString(_cursorIndexOfImageList);
        _tmpImageList = Converters.stringToSomeObjectList(_tmp_4);
        final boolean _tmpFavorite;
        final int _tmp_5;
        _tmp_5 = _cursor.getInt(_cursorIndexOfFavorite);
        _tmpFavorite = _tmp_5 != 0;
        _item = new DbPlace(_tmpIdCloud,_tmpTittle,_tmpResume,_tmpDetail,_tmpAddress,_tmpWebPage,_tmpPhone,_tmpIdDistritNeighborhood,_tmpLat,_tmpLng,_tmpActive,_tmpIsDeleted,_tmpTextTags,_tmpTextTagsList,_tmpInterviewed,_tmpImageList,_tmpFavorite);
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
