package com.avances.lima.interactor.event;

import com.avances.lima.domain.model.Event;

import java.util.List;

public interface EventListCallback {

    void onGetEventListSuccess(List<Event> events);
    void onGetEventListError(String message);
}
