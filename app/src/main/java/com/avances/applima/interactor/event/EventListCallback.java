package com.avances.applima.interactor.event;

import com.avances.applima.domain.model.Event;

import java.util.List;

public interface EventListCallback {

    void onGetEventListSuccess(List<Event> events);
    void onGetEventListError(String message);
}
