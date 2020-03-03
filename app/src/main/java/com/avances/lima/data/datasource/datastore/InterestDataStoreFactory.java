package com.avances.lima.data.datasource.datastore;

import android.content.Context;

import com.avances.lima.data.datasource.cloud.store.CloudInterestDataStore;
import com.avances.lima.data.datasource.db.store.DbInterestDataStore;

public class InterestDataStoreFactory {

    public static final int DB = 1;
    public static final int CLOUD = 0;// Constants.SOURCE_TYPE.CLOUD;

    private final Context context;

    public InterestDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        this.context = context.getApplicationContext();
    }


    public InterestDataStore create(int dataSource) {
        InterestDataStore interestDataStore = null;

        switch (dataSource) {
            case CLOUD:
                interestDataStore = createCloudDataStore();
                break;
            case DB:
                interestDataStore = new DbInterestDataStore(context);
                break;
        }
        return interestDataStore;
    }

    private InterestDataStore createCloudDataStore() {
        return new CloudInterestDataStore();
    }


}
