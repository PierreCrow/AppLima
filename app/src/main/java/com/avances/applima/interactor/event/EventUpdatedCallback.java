package com.avances.applima.interactor.event;

public interface EventUpdatedCallback {

    void onEventUpdatedSuccess(String message);

    void onEventUpdatedError(String message);
}
