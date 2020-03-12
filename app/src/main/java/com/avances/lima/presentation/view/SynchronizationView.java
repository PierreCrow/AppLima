package com.avances.lima.presentation.view;

import com.avances.lima.data.datasource.cloud.model.synchronization.WsSynchronization;

public interface SynchronizationView extends BaseView {

    void syncSuccesfull(WsSynchronization wsData);
    void verifiedSync(boolean sync);
    void showLoading();
    void hideLoading();
    void showErrorMessage(String message);
}
