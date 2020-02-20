package com.avances.applima.data.datasource.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.avances.applima.data.datasource.db.dao.CountryDao;
import com.avances.applima.data.datasource.db.dao.CountryDao_Impl;
import com.avances.applima.data.datasource.db.dao.DistritNeighborhoodDao;
import com.avances.applima.data.datasource.db.dao.DistritNeighborhoodDao_Impl;
import com.avances.applima.data.datasource.db.dao.EventDao;
import com.avances.applima.data.datasource.db.dao.EventDao_Impl;
import com.avances.applima.data.datasource.db.dao.GenderDao;
import com.avances.applima.data.datasource.db.dao.GenderDao_Impl;
import com.avances.applima.data.datasource.db.dao.InterestDao;
import com.avances.applima.data.datasource.db.dao.InterestDao_Impl;
import com.avances.applima.data.datasource.db.dao.PlaceDao;
import com.avances.applima.data.datasource.db.dao.PlaceDao_Impl;
import com.avances.applima.data.datasource.db.dao.RouteDao;
import com.avances.applima.data.datasource.db.dao.RouteDao_Impl;
import com.avances.applima.data.datasource.db.dao.SuggestedTagDao;
import com.avances.applima.data.datasource.db.dao.SuggestedTagDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class AppLimaDb_Impl extends AppLimaDb {
  private volatile PlaceDao _placeDao;

  private volatile InterestDao _interestDao;

  private volatile DistritNeighborhoodDao _distritNeighborhoodDao;

  private volatile RouteDao _routeDao;

  private volatile EventDao _eventDao;

  private volatile CountryDao _countryDao;

  private volatile GenderDao _genderDao;

  private volatile SuggestedTagDao _suggestedTagDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(9) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DbPlace` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `idCloud` TEXT, `tittle` TEXT, `resume` TEXT, `detail` TEXT, `address` TEXT, `webPage` TEXT, `phone` TEXT, `idDistritNeighborhood` TEXT, `lat` TEXT, `lng` TEXT, `active` INTEGER NOT NULL, `isDeleted` INTEGER NOT NULL, `textTags` TEXT, `textTagsList` TEXT, `interviewed` TEXT, `imageList` TEXT, `favorite` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DbInterest` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `idCloud` TEXT, `nameParameterValue` TEXT, `detailParameterValue` TEXT, `active` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DbDistritNeighborhood` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `idCloud` TEXT, `idDistritType` TEXT, `distrit` TEXT, `shortDescription` TEXT, `completeDescription` TEXT, `imageList` TEXT, `active` INTEGER NOT NULL, `idPlaceList` TEXT, `latitude` TEXT, `longitude` TEXT, `urlVideo` TEXT, `tags` TEXT, `tagList` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DbRoute` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `idCloud` TEXT, `idRouteType` TEXT, `routeName` TEXT, `idUserRegister` TEXT, `idUserModify` TEXT, `registerDate` TEXT, `modifyDate` TEXT, `isDeleted` INTEGER NOT NULL, `image` TEXT, `iconImage` TEXT, `idPlaceList` TEXT, `tags` TEXT, `tagList` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DbEvent` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `idCloud` TEXT, `tittle` TEXT, `shortDecription` TEXT, `description` TEXT, `eventDate` TEXT, `eventTime` TEXT, `idEventType` TEXT, `active` INTEGER NOT NULL, `isDeleted` INTEGER NOT NULL, `image` TEXT, `startDate` TEXT, `finalDate` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DbCountry` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `idCloud` TEXT, `nameParameterValue` TEXT, `detailParameterValue` TEXT, `active` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DbGender` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `idCloud` TEXT, `nameParameterValue` TEXT, `detailParameterValue` TEXT, `active` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DbSuggestedTag` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"14f90b61de0260d2cdafaf9260b8d645\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `DbPlace`");
        _db.execSQL("DROP TABLE IF EXISTS `DbInterest`");
        _db.execSQL("DROP TABLE IF EXISTS `DbDistritNeighborhood`");
        _db.execSQL("DROP TABLE IF EXISTS `DbRoute`");
        _db.execSQL("DROP TABLE IF EXISTS `DbEvent`");
        _db.execSQL("DROP TABLE IF EXISTS `DbCountry`");
        _db.execSQL("DROP TABLE IF EXISTS `DbGender`");
        _db.execSQL("DROP TABLE IF EXISTS `DbSuggestedTag`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsDbPlace = new HashMap<String, TableInfo.Column>(18);
        _columnsDbPlace.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsDbPlace.put("idCloud", new TableInfo.Column("idCloud", "TEXT", false, 0));
        _columnsDbPlace.put("tittle", new TableInfo.Column("tittle", "TEXT", false, 0));
        _columnsDbPlace.put("resume", new TableInfo.Column("resume", "TEXT", false, 0));
        _columnsDbPlace.put("detail", new TableInfo.Column("detail", "TEXT", false, 0));
        _columnsDbPlace.put("address", new TableInfo.Column("address", "TEXT", false, 0));
        _columnsDbPlace.put("webPage", new TableInfo.Column("webPage", "TEXT", false, 0));
        _columnsDbPlace.put("phone", new TableInfo.Column("phone", "TEXT", false, 0));
        _columnsDbPlace.put("idDistritNeighborhood", new TableInfo.Column("idDistritNeighborhood", "TEXT", false, 0));
        _columnsDbPlace.put("lat", new TableInfo.Column("lat", "TEXT", false, 0));
        _columnsDbPlace.put("lng", new TableInfo.Column("lng", "TEXT", false, 0));
        _columnsDbPlace.put("active", new TableInfo.Column("active", "INTEGER", true, 0));
        _columnsDbPlace.put("isDeleted", new TableInfo.Column("isDeleted", "INTEGER", true, 0));
        _columnsDbPlace.put("textTags", new TableInfo.Column("textTags", "TEXT", false, 0));
        _columnsDbPlace.put("textTagsList", new TableInfo.Column("textTagsList", "TEXT", false, 0));
        _columnsDbPlace.put("interviewed", new TableInfo.Column("interviewed", "TEXT", false, 0));
        _columnsDbPlace.put("imageList", new TableInfo.Column("imageList", "TEXT", false, 0));
        _columnsDbPlace.put("favorite", new TableInfo.Column("favorite", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDbPlace = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDbPlace = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDbPlace = new TableInfo("DbPlace", _columnsDbPlace, _foreignKeysDbPlace, _indicesDbPlace);
        final TableInfo _existingDbPlace = TableInfo.read(_db, "DbPlace");
        if (! _infoDbPlace.equals(_existingDbPlace)) {
          throw new IllegalStateException("Migration didn't properly handle DbPlace(com.avances.applima.data.datasource.db.model.DbPlace).\n"
                  + " Expected:\n" + _infoDbPlace + "\n"
                  + " Found:\n" + _existingDbPlace);
        }
        final HashMap<String, TableInfo.Column> _columnsDbInterest = new HashMap<String, TableInfo.Column>(5);
        _columnsDbInterest.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsDbInterest.put("idCloud", new TableInfo.Column("idCloud", "TEXT", false, 0));
        _columnsDbInterest.put("nameParameterValue", new TableInfo.Column("nameParameterValue", "TEXT", false, 0));
        _columnsDbInterest.put("detailParameterValue", new TableInfo.Column("detailParameterValue", "TEXT", false, 0));
        _columnsDbInterest.put("active", new TableInfo.Column("active", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDbInterest = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDbInterest = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDbInterest = new TableInfo("DbInterest", _columnsDbInterest, _foreignKeysDbInterest, _indicesDbInterest);
        final TableInfo _existingDbInterest = TableInfo.read(_db, "DbInterest");
        if (! _infoDbInterest.equals(_existingDbInterest)) {
          throw new IllegalStateException("Migration didn't properly handle DbInterest(com.avances.applima.data.datasource.db.model.DbInterest).\n"
                  + " Expected:\n" + _infoDbInterest + "\n"
                  + " Found:\n" + _existingDbInterest);
        }
        final HashMap<String, TableInfo.Column> _columnsDbDistritNeighborhood = new HashMap<String, TableInfo.Column>(14);
        _columnsDbDistritNeighborhood.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsDbDistritNeighborhood.put("idCloud", new TableInfo.Column("idCloud", "TEXT", false, 0));
        _columnsDbDistritNeighborhood.put("idDistritType", new TableInfo.Column("idDistritType", "TEXT", false, 0));
        _columnsDbDistritNeighborhood.put("distrit", new TableInfo.Column("distrit", "TEXT", false, 0));
        _columnsDbDistritNeighborhood.put("shortDescription", new TableInfo.Column("shortDescription", "TEXT", false, 0));
        _columnsDbDistritNeighborhood.put("completeDescription", new TableInfo.Column("completeDescription", "TEXT", false, 0));
        _columnsDbDistritNeighborhood.put("imageList", new TableInfo.Column("imageList", "TEXT", false, 0));
        _columnsDbDistritNeighborhood.put("active", new TableInfo.Column("active", "INTEGER", true, 0));
        _columnsDbDistritNeighborhood.put("idPlaceList", new TableInfo.Column("idPlaceList", "TEXT", false, 0));
        _columnsDbDistritNeighborhood.put("latitude", new TableInfo.Column("latitude", "TEXT", false, 0));
        _columnsDbDistritNeighborhood.put("longitude", new TableInfo.Column("longitude", "TEXT", false, 0));
        _columnsDbDistritNeighborhood.put("urlVideo", new TableInfo.Column("urlVideo", "TEXT", false, 0));
        _columnsDbDistritNeighborhood.put("tags", new TableInfo.Column("tags", "TEXT", false, 0));
        _columnsDbDistritNeighborhood.put("tagList", new TableInfo.Column("tagList", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDbDistritNeighborhood = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDbDistritNeighborhood = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDbDistritNeighborhood = new TableInfo("DbDistritNeighborhood", _columnsDbDistritNeighborhood, _foreignKeysDbDistritNeighborhood, _indicesDbDistritNeighborhood);
        final TableInfo _existingDbDistritNeighborhood = TableInfo.read(_db, "DbDistritNeighborhood");
        if (! _infoDbDistritNeighborhood.equals(_existingDbDistritNeighborhood)) {
          throw new IllegalStateException("Migration didn't properly handle DbDistritNeighborhood(com.avances.applima.data.datasource.db.model.DbDistritNeighborhood).\n"
                  + " Expected:\n" + _infoDbDistritNeighborhood + "\n"
                  + " Found:\n" + _existingDbDistritNeighborhood);
        }
        final HashMap<String, TableInfo.Column> _columnsDbRoute = new HashMap<String, TableInfo.Column>(14);
        _columnsDbRoute.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsDbRoute.put("idCloud", new TableInfo.Column("idCloud", "TEXT", false, 0));
        _columnsDbRoute.put("idRouteType", new TableInfo.Column("idRouteType", "TEXT", false, 0));
        _columnsDbRoute.put("routeName", new TableInfo.Column("routeName", "TEXT", false, 0));
        _columnsDbRoute.put("idUserRegister", new TableInfo.Column("idUserRegister", "TEXT", false, 0));
        _columnsDbRoute.put("idUserModify", new TableInfo.Column("idUserModify", "TEXT", false, 0));
        _columnsDbRoute.put("registerDate", new TableInfo.Column("registerDate", "TEXT", false, 0));
        _columnsDbRoute.put("modifyDate", new TableInfo.Column("modifyDate", "TEXT", false, 0));
        _columnsDbRoute.put("isDeleted", new TableInfo.Column("isDeleted", "INTEGER", true, 0));
        _columnsDbRoute.put("image", new TableInfo.Column("image", "TEXT", false, 0));
        _columnsDbRoute.put("iconImage", new TableInfo.Column("iconImage", "TEXT", false, 0));
        _columnsDbRoute.put("idPlaceList", new TableInfo.Column("idPlaceList", "TEXT", false, 0));
        _columnsDbRoute.put("tags", new TableInfo.Column("tags", "TEXT", false, 0));
        _columnsDbRoute.put("tagList", new TableInfo.Column("tagList", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDbRoute = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDbRoute = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDbRoute = new TableInfo("DbRoute", _columnsDbRoute, _foreignKeysDbRoute, _indicesDbRoute);
        final TableInfo _existingDbRoute = TableInfo.read(_db, "DbRoute");
        if (! _infoDbRoute.equals(_existingDbRoute)) {
          throw new IllegalStateException("Migration didn't properly handle DbRoute(com.avances.applima.data.datasource.db.model.DbRoute).\n"
                  + " Expected:\n" + _infoDbRoute + "\n"
                  + " Found:\n" + _existingDbRoute);
        }
        final HashMap<String, TableInfo.Column> _columnsDbEvent = new HashMap<String, TableInfo.Column>(13);
        _columnsDbEvent.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsDbEvent.put("idCloud", new TableInfo.Column("idCloud", "TEXT", false, 0));
        _columnsDbEvent.put("tittle", new TableInfo.Column("tittle", "TEXT", false, 0));
        _columnsDbEvent.put("shortDecription", new TableInfo.Column("shortDecription", "TEXT", false, 0));
        _columnsDbEvent.put("description", new TableInfo.Column("description", "TEXT", false, 0));
        _columnsDbEvent.put("eventDate", new TableInfo.Column("eventDate", "TEXT", false, 0));
        _columnsDbEvent.put("eventTime", new TableInfo.Column("eventTime", "TEXT", false, 0));
        _columnsDbEvent.put("idEventType", new TableInfo.Column("idEventType", "TEXT", false, 0));
        _columnsDbEvent.put("active", new TableInfo.Column("active", "INTEGER", true, 0));
        _columnsDbEvent.put("isDeleted", new TableInfo.Column("isDeleted", "INTEGER", true, 0));
        _columnsDbEvent.put("image", new TableInfo.Column("image", "TEXT", false, 0));
        _columnsDbEvent.put("startDate", new TableInfo.Column("startDate", "TEXT", false, 0));
        _columnsDbEvent.put("finalDate", new TableInfo.Column("finalDate", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDbEvent = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDbEvent = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDbEvent = new TableInfo("DbEvent", _columnsDbEvent, _foreignKeysDbEvent, _indicesDbEvent);
        final TableInfo _existingDbEvent = TableInfo.read(_db, "DbEvent");
        if (! _infoDbEvent.equals(_existingDbEvent)) {
          throw new IllegalStateException("Migration didn't properly handle DbEvent(com.avances.applima.data.datasource.db.model.DbEvent).\n"
                  + " Expected:\n" + _infoDbEvent + "\n"
                  + " Found:\n" + _existingDbEvent);
        }
        final HashMap<String, TableInfo.Column> _columnsDbCountry = new HashMap<String, TableInfo.Column>(5);
        _columnsDbCountry.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsDbCountry.put("idCloud", new TableInfo.Column("idCloud", "TEXT", false, 0));
        _columnsDbCountry.put("nameParameterValue", new TableInfo.Column("nameParameterValue", "TEXT", false, 0));
        _columnsDbCountry.put("detailParameterValue", new TableInfo.Column("detailParameterValue", "TEXT", false, 0));
        _columnsDbCountry.put("active", new TableInfo.Column("active", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDbCountry = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDbCountry = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDbCountry = new TableInfo("DbCountry", _columnsDbCountry, _foreignKeysDbCountry, _indicesDbCountry);
        final TableInfo _existingDbCountry = TableInfo.read(_db, "DbCountry");
        if (! _infoDbCountry.equals(_existingDbCountry)) {
          throw new IllegalStateException("Migration didn't properly handle DbCountry(com.avances.applima.data.datasource.db.model.DbCountry).\n"
                  + " Expected:\n" + _infoDbCountry + "\n"
                  + " Found:\n" + _existingDbCountry);
        }
        final HashMap<String, TableInfo.Column> _columnsDbGender = new HashMap<String, TableInfo.Column>(5);
        _columnsDbGender.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsDbGender.put("idCloud", new TableInfo.Column("idCloud", "TEXT", false, 0));
        _columnsDbGender.put("nameParameterValue", new TableInfo.Column("nameParameterValue", "TEXT", false, 0));
        _columnsDbGender.put("detailParameterValue", new TableInfo.Column("detailParameterValue", "TEXT", false, 0));
        _columnsDbGender.put("active", new TableInfo.Column("active", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDbGender = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDbGender = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDbGender = new TableInfo("DbGender", _columnsDbGender, _foreignKeysDbGender, _indicesDbGender);
        final TableInfo _existingDbGender = TableInfo.read(_db, "DbGender");
        if (! _infoDbGender.equals(_existingDbGender)) {
          throw new IllegalStateException("Migration didn't properly handle DbGender(com.avances.applima.data.datasource.db.model.DbGender).\n"
                  + " Expected:\n" + _infoDbGender + "\n"
                  + " Found:\n" + _existingDbGender);
        }
        final HashMap<String, TableInfo.Column> _columnsDbSuggestedTag = new HashMap<String, TableInfo.Column>(2);
        _columnsDbSuggestedTag.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsDbSuggestedTag.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDbSuggestedTag = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDbSuggestedTag = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDbSuggestedTag = new TableInfo("DbSuggestedTag", _columnsDbSuggestedTag, _foreignKeysDbSuggestedTag, _indicesDbSuggestedTag);
        final TableInfo _existingDbSuggestedTag = TableInfo.read(_db, "DbSuggestedTag");
        if (! _infoDbSuggestedTag.equals(_existingDbSuggestedTag)) {
          throw new IllegalStateException("Migration didn't properly handle DbSuggestedTag(com.avances.applima.data.datasource.db.model.DbSuggestedTag).\n"
                  + " Expected:\n" + _infoDbSuggestedTag + "\n"
                  + " Found:\n" + _existingDbSuggestedTag);
        }
      }
    }, "14f90b61de0260d2cdafaf9260b8d645", "31ca360dba4590e2d374890c3959ca83");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "DbPlace","DbInterest","DbDistritNeighborhood","DbRoute","DbEvent","DbCountry","DbGender","DbSuggestedTag");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `DbPlace`");
      _db.execSQL("DELETE FROM `DbInterest`");
      _db.execSQL("DELETE FROM `DbDistritNeighborhood`");
      _db.execSQL("DELETE FROM `DbRoute`");
      _db.execSQL("DELETE FROM `DbEvent`");
      _db.execSQL("DELETE FROM `DbCountry`");
      _db.execSQL("DELETE FROM `DbGender`");
      _db.execSQL("DELETE FROM `DbSuggestedTag`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public PlaceDao placeDao() {
    if (_placeDao != null) {
      return _placeDao;
    } else {
      synchronized(this) {
        if(_placeDao == null) {
          _placeDao = new PlaceDao_Impl(this);
        }
        return _placeDao;
      }
    }
  }

  @Override
  public InterestDao interestDao() {
    if (_interestDao != null) {
      return _interestDao;
    } else {
      synchronized(this) {
        if(_interestDao == null) {
          _interestDao = new InterestDao_Impl(this);
        }
        return _interestDao;
      }
    }
  }

  @Override
  public DistritNeighborhoodDao distritNeighborhoodDao() {
    if (_distritNeighborhoodDao != null) {
      return _distritNeighborhoodDao;
    } else {
      synchronized(this) {
        if(_distritNeighborhoodDao == null) {
          _distritNeighborhoodDao = new DistritNeighborhoodDao_Impl(this);
        }
        return _distritNeighborhoodDao;
      }
    }
  }

  @Override
  public RouteDao routeDao() {
    if (_routeDao != null) {
      return _routeDao;
    } else {
      synchronized(this) {
        if(_routeDao == null) {
          _routeDao = new RouteDao_Impl(this);
        }
        return _routeDao;
      }
    }
  }

  @Override
  public EventDao eventDao() {
    if (_eventDao != null) {
      return _eventDao;
    } else {
      synchronized(this) {
        if(_eventDao == null) {
          _eventDao = new EventDao_Impl(this);
        }
        return _eventDao;
      }
    }
  }

  @Override
  public CountryDao countryDao() {
    if (_countryDao != null) {
      return _countryDao;
    } else {
      synchronized(this) {
        if(_countryDao == null) {
          _countryDao = new CountryDao_Impl(this);
        }
        return _countryDao;
      }
    }
  }

  @Override
  public GenderDao genderDao() {
    if (_genderDao != null) {
      return _genderDao;
    } else {
      synchronized(this) {
        if(_genderDao == null) {
          _genderDao = new GenderDao_Impl(this);
        }
        return _genderDao;
      }
    }
  }

  @Override
  public SuggestedTagDao suggestedTagDao() {
    if (_suggestedTagDao != null) {
      return _suggestedTagDao;
    } else {
      synchronized(this) {
        if(_suggestedTagDao == null) {
          _suggestedTagDao = new SuggestedTagDao_Impl(this);
        }
        return _suggestedTagDao;
      }
    }
  }
}
