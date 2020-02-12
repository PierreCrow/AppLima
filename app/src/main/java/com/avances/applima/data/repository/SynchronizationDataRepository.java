package com.avances.applima.data.repository;

import com.avances.applima.data.datasource.cloud.model.synchronization.WsSynchronization;
import com.avances.applima.data.datasource.datastore.SynchronizationDataStore;
import com.avances.applima.data.datasource.datastore.SynchronizationDataStoreFactory;
import com.avances.applima.domain.repository.RepositoryCallback;
import com.avances.applima.domain.repository.SynchronizationRepository;
import com.avances.applima.interactor.synchronization.SynchronizationCallback;

public class SynchronizationDataRepository implements SynchronizationRepository {

    private final SynchronizationDataStoreFactory synchronizationDataStoreFactory;

    public SynchronizationDataRepository(SynchronizationDataStoreFactory synchronizationDataStoreFactory) {
        this.synchronizationDataStoreFactory = synchronizationDataStoreFactory;
    }


    @Override
    public void syncAll(String token,int store,SynchronizationCallback synchronizationCallback) {

        final SynchronizationDataStore synchronizationDataStore=synchronizationDataStoreFactory.create(store);
        synchronizationDataStore.syncAll(token,new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                synchronizationCallback.onSyncError(message);
            }

            @Override
            public void onSuccess(Object object) {
                WsSynchronization wsAffectationIgvs  = (WsSynchronization) object;
                synchronizationCallback.onSyncSuccess(wsAffectationIgvs);
            }
        });
    }
}
