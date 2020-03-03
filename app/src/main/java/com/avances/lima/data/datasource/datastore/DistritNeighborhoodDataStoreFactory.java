package com.avances.lima.data.datasource.datastore;

import android.content.Context;

import com.avances.lima.data.datasource.cloud.store.CloudDistritNeighborhoodDataStore;
import com.avances.lima.data.datasource.db.store.DbDistritNeighborhoodDataStore;

public class DistritNeighborhoodDataStoreFactory {

    public static final int DB = 1;
    public static final int CLOUD = 0;// Constants.SOURCE_TYPE.CLOUD;

    private final Context context;

    public DistritNeighborhoodDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        this.context = context.getApplicationContext();
    }


    public DistritNeighborhoodDataStore create(int dataSource) {
        DistritNeighborhoodDataStore usuarioDataStore = null;

        switch (dataSource) {
            case CLOUD:
                usuarioDataStore = createCloudDataStore();
                break;
            case DB:
                usuarioDataStore = new DbDistritNeighborhoodDataStore(context);
                break;
        }
        return usuarioDataStore;
    }

    private DistritNeighborhoodDataStore createCloudDataStore() {
        return new CloudDistritNeighborhoodDataStore();
    }


}
