package com.avances.lima.data.datasource.datastore;

import android.content.Context;

import com.avances.lima.data.datasource.cloud.store.CloudCountryDataStore;
import com.avances.lima.data.datasource.db.store.DbCountryDataStore;

public class CountryDataStoreFactory {

    public static final int DB = 1;
    public static final int CLOUD = 0;// Constants.SOURCE_TYPE.CLOUD;

    private final Context context;

    public CountryDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        this.context = context.getApplicationContext();
    }


    public CountryDataStore create(int dataSource) {
        CountryDataStore interestDataStore = null;

        switch (dataSource) {
            case CLOUD:
                interestDataStore = createCloudDataStore();
                break;
            case DB:
                interestDataStore = new DbCountryDataStore(context);
                break;
        }
        return interestDataStore;
    }

    private CountryDataStore createCloudDataStore() {
        return new CloudCountryDataStore();
    }


}
