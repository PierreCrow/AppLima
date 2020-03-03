package com.avances.lima.interactor.event;

public interface EventUpdatedCallback {

    void onEventUpdatedSuccess(String message);

    void onEventUpdatedError(String message);
}
