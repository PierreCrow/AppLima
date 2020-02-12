package com.avances.applima.data.datasource.datastore;

import android.content.Context;

import com.avances.applima.data.datasource.cloud.store.CloudEventDataStore;
import com.avances.applima.data.datasource.cloud.store.CloudPlaceDataStore;
import com.avances.applima.data.datasource.db.store.DbEventDataStore;
import com.avances.applima.data.datasource.db.store.DbPlaceDataStore;

public class EventDataStoreFactory {

    public static final int DB = 1;
    public static final int CLOUD = 0;// Constants.SOURCE_TYPE.CLOUD;

    private final Context context;

    public EventDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        this.context = context.getApplicationContext();
    }


    public EventDataStore create(int dataSource) {
        EventDataStore placeDataStore = null;

        switch (dataSource) {
            case CLOUD:
                placeDataStore = createCloudDataStore();
                break;
            case DB:
                placeDataStore = new DbEventDataStore(context);
                break;
        }
        return placeDataStore;
    }

    private EventDataStore createCloudDataStore() {
        return new CloudEventDataStore();
    }


}
