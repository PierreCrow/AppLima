package com.avances.lima.interactor.synchronization;


public interface VerifySyncCallback {

    void onVerifySyncSuccess(boolean sync);

    void onVerifySyncError(String message);
}
