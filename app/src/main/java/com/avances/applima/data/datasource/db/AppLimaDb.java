package com.avances.applima.data.datasource.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.avances.applima.data.datasource.db.dao.CountryDao;
import com.avances.applima.data.datasource.db.dao.DistritNeighborhoodDao;
import com.avances.applima.data.datasource.db.dao.EventDao;
import com.avances.applima.data.datasource.db.dao.GenderDao;
import com.avances.applima.data.datasource.db.dao.InterestDao;
import com.avances.applima.data.datasource.db.dao.PlaceDao;
import com.avances.applima.data.datasource.db.dao.RouteDao;
import com.avances.applima.data.datasource.db.dao.SuggestedTagDao;
import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.applima.data.datasource.db.model.DbEvent;
import com.avances.applima.data.datasource.db.model.DbGender;
import com.avances.applima.data.datasource.db.model.DbInterest;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.data.datasource.db.model.DbRoute;
import com.avances.applima.data.datasource.db.model.DbSuggestedTag;
import com.avances.applima.data.datasource.db.model.DbUsuario;
import com.avances.applima.domain.model.DistritNeighborhood;
import com.avances.applima.presentation.utils.Converters;

@Database(entities = {
        DbPlace.class,
        DbInterest.class,
        DbDistritNeighborhood.class,
        DbRoute.class,
        DbEvent.class,
        DbCountry.class,
        DbGender.class,
        DbSuggestedTag.class},
        version = 9, exportSchema = false)
@TypeConverters({Converters.class})

public abstract class AppLimaDb extends RoomDatabase {


    private static AppLimaDb INSTANCE;
    private static final String DB_NAME = "appLima.db";
    private static Context mContext;

    public static AppLimaDb getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppLimaDb.class) {
                if (INSTANCE == null) {
                    mContext = context.getApplicationContext();
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppLimaDb.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries() // SHOULD NOT BE USED IN PRODUCTION !!!
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    // Log.i(TAG, "populating with data...");
//                                    new PopulateDbAsync(INSTANCE).execute();
                                }
                            }).build();
                }
            }
        }
        return INSTANCE;
    }


    public abstract PlaceDao placeDao();

    public abstract InterestDao interestDao();

    public abstract DistritNeighborhoodDao distritNeighborhoodDao();

    public abstract RouteDao routeDao();

    public abstract EventDao eventDao();

    public abstract CountryDao countryDao();

    public abstract GenderDao genderDao();

    public abstract SuggestedTagDao suggestedTagDao();

}
