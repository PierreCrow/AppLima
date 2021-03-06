package com.avances.lima.data.datasource.datastore;

import android.content.Context;

import com.avances.lima.data.datasource.cloud.store.CloudPlaceDataStore;
import com.avances.lima.data.datasource.db.store.DbPlaceDataStore;

public class PlaceDataStoreFactory {

    public static final int DB = 1;
    public static final int CLOUD = 0;// Constants.SOURCE_TYPE.CLOUD;

    private final Context context;

    public PlaceDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        this.context = context.getApplicationContext();
    }


    public PlaceDataStore create(int dataSource) {
        PlaceDataStore placeDataStore = null;

        switch (dataSource) {
            case CLOUD:
                placeDataStore = createCloudDataStore();
                break;
            case DB:
                placeDataStore = new DbPlaceDataStore(context);
                break;
        }
        return placeDataStore;
    }

    private PlaceDataStore createCloudDataStore() {
        return new CloudPlaceDataStore();
    }


}
