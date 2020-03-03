package com.avances.lima.presentation.presenter;

import com.avances.lima.data.datasource.cloud.model.synchronization.WsSynchronization;
import com.avances.lima.data.datasource.datastore.SynchronizationDataStoreFactory;
import com.avances.lima.data.repository.SynchronizationDataRepository;
import com.avances.lima.domain.repository.SynchronizationRepository;
import com.avances.lima.interactor.synchronization.SynchronizationCallback;
import com.avances.lima.interactor.synchronization.SynchronizationInteractor;
import com.avances.lima.presentation.view.SynchronizationView;

public class SynchronizationPresenter implements Presenter<SynchronizationView>, SynchronizationCallback {

    private SynchronizationView synchronizationView;
    private SynchronizationInteractor synchronizationInteractor;


    public void syncAll(String token,int store) {
        synchronizationInteractor.syncAll(token,store,this);
    }



    @Override
    public void onSyncSuccess(WsSynchronization wsData) {
        synchronizationView.syncSuccesfull(wsData);
    }

    @Override
    public void onSyncError(String message) {

    }

    @Override
    public void addView(SynchronizationView view) {
        this.synchronizationView = view;
        SynchronizationRepository requestRepository = new SynchronizationDataRepository(new SynchronizationDataStoreFactory(this.synchronizationView.getContext()));
        synchronizationInteractor = new SynchronizationInteractor(requestRepository);
    }

    @Override
    public void removeView(SynchronizationView view) {

    }
}