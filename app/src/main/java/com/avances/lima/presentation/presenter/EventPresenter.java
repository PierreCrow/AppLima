package com.avances.lima.presentation.presenter;

import com.avances.lima.data.datasource.datastore.EventDataStoreFactory;
import com.avances.lima.data.datasource.db.model.DbEvent;
import com.avances.lima.data.repository.EventDataRepository;
import com.avances.lima.domain.model.Event;
import com.avances.lima.domain.repository.EventRepository;
import com.avances.lima.interactor.event.EventCreatedCallback;
import com.avances.lima.interactor.event.EventInteractor;
import com.avances.lima.interactor.event.EventListCallback;
import com.avances.lima.interactor.event.EventUpdatedCallback;
import com.avances.lima.presentation.view.EventView;

import java.util.List;

public class EventPresenter implements Presenter<EventView>, EventListCallback, EventCreatedCallback, EventUpdatedCallback {

    private EventView eventView;
    private EventInteractor eventInteractor;


    public void getEvents(int store) {
        eventInteractor.getEvents(store, this);
    }

    public void createEvents(List<DbEvent> dbEvents, int store) {
        eventInteractor.createEvents(dbEvents, store, this);
    }

    public void updateEvent(DbEvent dbEvent, int store) {
        eventInteractor.updateEvent(dbEvent, store, this);
    }


    @Override
    public void onEventCreatedSuccess(String message) {
        eventView.eventCreated(message);
    }

    @Override
    public void onEventCreatedError(String message) {
        eventView.showErrorMessage(message);
    }

    @Override
    public void onGetEventListSuccess(List<Event> events) {
        eventView.eventListLoaded(events);
    }

    @Override
    public void onGetEventListError(String message) {
        eventView.showErrorMessage(message);
    }

    @Override
    public void onEventUpdatedSuccess(String message) {
        eventView.eventUpdated(message);
    }

    @Override
    public void onEventUpdatedError(String message) {
        eventView.showErrorMessage(message);
    }

    @Override
    public void addView(EventView view) {
        this.eventView = view;
        EventRepository eventRepository = new EventDataRepository(new EventDataStoreFactory(this.eventView.getContext()));
        eventInteractor = new EventInteractor(eventRepository);
    }

    @Override
    public void removeView(EventView view) {

    }
}
