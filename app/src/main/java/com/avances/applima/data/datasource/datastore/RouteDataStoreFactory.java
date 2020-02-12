package com.avances.applima.data.datasource.datastore;

import android.content.Context;

import com.avances.applima.data.datasource.cloud.store.CloudPlaceDataStore;
import com.avances.applima.data.datasource.cloud.store.CloudRouteDataStore;
import com.avances.applima.data.datasource.db.store.DbPlaceDataStore;
import com.avances.applima.data.datasource.db.store.DbRouteDataStore;

public class RouteDataStoreFactory {

    public static final int DB = 1;
    public static final int CLOUD = 0;// Constants.SOURCE_TYPE.CLOUD;

    private final Context context;

    public RouteDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        this.context = context.getApplicationContext();
    }


    public RouteDataStore create(int dataSource) {
        RouteDataStore placeDataStore = null;

        switch (dataSource) {
            case CLOUD:
                placeDataStore = createCloudDataStore();
                break;
            case DB:
                placeDataStore = new DbRouteDataStore(context);
                break;
        }
        return placeDataStore;
    }

    private RouteDataStore createCloudDataStore() {
        return new CloudRouteDataStore();
    }


}
