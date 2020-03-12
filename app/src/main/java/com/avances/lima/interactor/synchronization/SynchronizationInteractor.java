package com.avances.lima.interactor.synchronization;

import com.avances.lima.domain.repository.SynchronizationRepository;

public class SynchronizationInteractor {

    private final SynchronizationRepository synchronizationRepository;

    public SynchronizationInteractor(SynchronizationRepository synchronizationRepository) {
        this.synchronizationRepository = synchronizationRepository;
    }

    public void syncAll(String token,int store,SynchronizationCallback synchronizationCallback) {
        synchronizationRepository.syncAll(token,store,synchronizationCallback);
    }

    public void verifySync(String token,String lastDate,VerifySyncCallback verifySyncCallback) {
        synchronizationRepository.verifySync(token,lastDate,verifySyncCallback);
    }

}
