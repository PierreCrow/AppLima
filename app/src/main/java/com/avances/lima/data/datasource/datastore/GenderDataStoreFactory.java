package com.avances.lima.data.datasource.datastore;

import android.content.Context;

import com.avances.lima.data.datasource.cloud.store.CloudGenderDataStore;
import com.avances.lima.data.datasource.db.store.DbGenderDataStore;

public class GenderDataStoreFactory {

    public static final int DB = 1;
    public static final int CLOUD = 0;// Constants.SOURCE_TYPE.CLOUD;

    private final Context context;

    public GenderDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        this.context = context.getApplicationContext();
    }


    public GenderDataStore create(int dataSource) {
        GenderDataStore interestDataStore = null;

        switch (dataSource) {
            case CLOUD:
                interestDataStore = createCloudDataStore();
                break;
            case DB:
                interestDataStore = new DbGenderDataStore(context);
                break;
        }
        return interestDataStore;
    }

    private GenderDataStore createCloudDataStore() {
        return new CloudGenderDataStore();
    }


}
