package com.avances.applima.domain.repository;

import com.avances.applima.interactor.synchronization.SynchronizationCallback;


public interface SynchronizationRepository {

    void syncAll(String token,int store, SynchronizationCallback synchronizationCallback);

}
