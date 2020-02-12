package com.avances.applima.presentation.presenter;

import com.avances.applima.data.datasource.datastore.EventDataStoreFactory;
import com.avances.applima.data.datasource.db.model.DbEvent;
import com.avances.applima.data.repository.EventDataRepository;
import com.avances.applima.domain.model.Event;
import com.avances.applima.domain.repository.EventRepository;
import com.avances.applima.interactor.event.EventCreatedCallback;
import com.avances.applima.interactor.event.EventInteractor;
import com.avances.applima.interactor.event.EventListCallback;
import com.avances.applima.interactor.event.EventUpdatedCallback;
import com.avances.applima.presentation.view.EventView;

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
