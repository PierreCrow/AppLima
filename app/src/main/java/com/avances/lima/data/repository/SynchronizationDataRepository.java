package com.avances.lima.data.repository;

import com.avances.lima.data.datasource.cloud.model.synchronization.WsSynchronization;
import com.avances.lima.data.datasource.datastore.SynchronizationDataStore;
import com.avances.lima.data.datasource.datastore.SynchronizationDataStoreFactory;
import com.avances.lima.domain.repository.RepositoryCallback;
import com.avances.lima.domain.repository.SynchronizationRepository;
import com.avances.lima.interactor.synchronization.SynchronizationCallback;
import com.avances.lima.interactor.synchronization.VerifySyncCallback;
import com.avances.lima.presentation.utils.Constants;

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

    @Override
    public void verifySync(String token, String lastDateSync, VerifySyncCallback verifySyncCallback) {

        final SynchronizationDataStore synchronizationDataStore=synchronizationDataStoreFactory.create(Constants.STORE.CLOUD);
        synchronizationDataStore.verifySynchronization(token, lastDateSync, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                verifySyncCallback.onVerifySyncError(message);
            }

            @Override
            public void onSuccess(Object object) {

                boolean sync=(boolean)object;
                verifySyncCallback.onVerifySyncSuccess(sync);
            }
        });


    }
}
