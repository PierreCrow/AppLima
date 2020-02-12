package com.avances.applima.interactor.synchronization;

import com.avances.applima.domain.repository.SynchronizationRepository;

public class SynchronizationInteractor {

    private final SynchronizationRepository synchronizationRepository;

    public SynchronizationInteractor(SynchronizationRepository synchronizationRepository) {
        this.synchronizationRepository = synchronizationRepository;
    }

    public void syncAll(String token,int store,SynchronizationCallback synchronizationCallback) {
        synchronizationRepository.syncAll(token,store,synchronizationCallback);
    }

}
