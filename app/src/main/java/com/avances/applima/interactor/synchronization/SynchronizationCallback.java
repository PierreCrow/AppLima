package com.avances.applima.interactor.synchronization;

import com.avances.applima.data.datasource.cloud.model.synchronization.WsSynchronization;

public interface SynchronizationCallback {

    void onSyncSuccess(WsSynchronization wsAffectationIgvs);

    void onSyncError(String message);
}
