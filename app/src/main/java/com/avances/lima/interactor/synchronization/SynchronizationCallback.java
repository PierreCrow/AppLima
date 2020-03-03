package com.avances.lima.interactor.synchronization;

import com.avances.lima.data.datasource.cloud.model.synchronization.WsSynchronization;

public interface SynchronizationCallback {

    void onSyncSuccess(WsSynchronization wsAffectationIgvs);

    void onSyncError(String message);
}
