package com.avances.lima.data.datasource.datastore;

import android.content.Context;

import com.avances.lima.data.datasource.cloud.store.CloudCountryDataStore;
import com.avances.lima.data.datasource.cloud.store.CloudPermanencyDayDataStore;
import com.avances.lima.data.datasource.db.store.DbCountryDataStore;
import com.avances.lima.data.datasource.db.store.DbPermanencyDayDataStore;
import com.avances.lima.domain.model.PermanencyDay;

public class PermanencyDayDataStoreFactory {

    public static final int DB = 1;
    public static final int CLOUD = 0;// Constants.SOURCE_TYPE.CLOUD;

    private final Context context;

    public PermanencyDayDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        this.context = context.getApplicationContext();
    }


    public PermanencyDayDataStore create(int dataSource) {
        PermanencyDayDataStore interestDataStore = null;

        switch (dataSource) {
            case CLOUD:
                interestDataStore = createCloudDataStore();
                break;
            case DB:
                interestDataStore = new DbPermanencyDayDataStore(context);
                break;
        }
        return interestDataStore;
    }

    private PermanencyDayDataStore createCloudDataStore() {
        return new CloudPermanencyDayDataStore();
    }


}
