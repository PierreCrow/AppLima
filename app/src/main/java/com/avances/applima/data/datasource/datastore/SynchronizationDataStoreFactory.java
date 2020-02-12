package com.avances.applima.data.datasource.datastore;

import android.content.Context;

import com.avances.applima.data.datasource.cloud.store.CloudSynchronizationDataStore;
import com.avances.applima.data.datasource.cloud.store.CloudUsuarioDataStore;
import com.avances.applima.data.datasource.db.store.DbSynchronizationDataStore;
import com.avances.applima.data.datasource.db.store.DbUsuarioDataStore;

public class SynchronizationDataStoreFactory {

    public static final int DB = 1;
    public static final int CLOUD = 0;// Constants.SOURCE_TYPE.CLOUD;

    private final Context context;

    public SynchronizationDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        this.context = context.getApplicationContext();
    }


    public SynchronizationDataStore create(int dataSource) {
        SynchronizationDataStore usuarioDataStore = null;

        switch (dataSource) {
            case CLOUD:
                usuarioDataStore = createCloudDataStore();
                break;
            case DB:
                usuarioDataStore = new DbSynchronizationDataStore(context);
                break;
        }
        return usuarioDataStore;
    }

    private SynchronizationDataStore createCloudDataStore() {
        return new CloudSynchronizationDataStore();
    }


}
