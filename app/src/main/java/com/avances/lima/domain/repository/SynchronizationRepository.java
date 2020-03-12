package com.avances.lima.domain.repository;

import com.avances.lima.interactor.synchronization.SynchronizationCallback;
import com.avances.lima.interactor.synchronization.VerifySyncCallback;


public interface SynchronizationRepository {

    void syncAll(String token, int store, SynchronizationCallback synchronizationCallback);

    void verifySync(String token, String lastDateSync, VerifySyncCallback verifySyncCallback);

}
