package com.avances.lima.domain.repository;

import com.avances.lima.interactor.synchronization.SynchronizationCallback;


public interface SynchronizationRepository {

    void syncAll(String token,int store, SynchronizationCallback synchronizationCallback);

}
