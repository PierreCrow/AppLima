package com.avances.applima.interactor.event;

public interface EventCreatedCallback {

    void onEventCreatedSuccess(String message);

    void onEventCreatedError(String message);
}
