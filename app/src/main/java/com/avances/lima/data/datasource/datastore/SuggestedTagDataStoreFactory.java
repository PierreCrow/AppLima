package com.avances.lima.data.datasource.datastore;

import android.content.Context;

import com.avances.lima.data.datasource.cloud.store.CloudSuggestedTagDataStore;
import com.avances.lima.data.datasource.db.store.DbSuggestedTagDataStore;

public class SuggestedTagDataStoreFactory {

    public static final int DB = 1;
    public static final int CLOUD = 0;// Constants.SOURCE_TYPE.CLOUD;

    private final Context context;

    public SuggestedTagDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        this.context = context.getApplicationContext();
    }


    public SuggestedTagDataStore create(int dataSource) {
        SuggestedTagDataStore interestDataStore = null;

        switch (dataSource) {
            case CLOUD:
                interestDataStore = createCloudDataStore();
                break;
            case DB:
                interestDataStore = new DbSuggestedTagDataStore(context);
                break;
        }
        return interestDataStore;
    }

    private SuggestedTagDataStore createCloudDataStore() {
        return new CloudSuggestedTagDataStore();
    }


}
