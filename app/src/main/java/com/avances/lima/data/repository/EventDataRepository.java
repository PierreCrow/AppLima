package com.avances.lima.data.repository;

import com.avances.lima.data.datasource.datastore.EventDataStore;
import com.avances.lima.data.datasource.datastore.EventDataStoreFactory;
import com.avances.lima.data.datasource.db.model.DbEvent;
import com.avances.lima.domain.model.Event;
import com.avances.lima.domain.repository.EventRepository;
import com.avances.lima.domain.repository.RepositoryCallback;
import com.avances.lima.interactor.event.EventCreatedCallback;
import com.avances.lima.interactor.event.EventListCallback;
import com.avances.lima.interactor.event.EventUpdatedCallback;


import java.util.List;

public class EventDataRepository implements EventRepository {

    private final EventDataStoreFactory eventDataStoreFactory;

    public EventDataRepository(EventDataStoreFactory eventDataStoreFactory) {
        this.eventDataStoreFactory = eventDataStoreFactory;
    }


    @Override
    public void getEvents(int store, EventListCallback eventListCallback) {
        final EventDataStore eventDataStore = eventDataStoreFactory.create(store);
        eventDataStore.getEvents(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                eventListCallback.onGetEventListError(message);
            }

            @Override
            public void onSuccess(Object object) {

                List<Event> events = (List<Event>) object;
                eventListCallback.onGetEventListSuccess(events);
            }
        });
    }

    @Override
    public void createEvents(List<DbEvent> dbEvents, int store, EventCreatedCallback eventCreatedCallback) {
        final EventDataStore eventDataStore = eventDataStoreFactory.create(store);
        eventDataStore.createEvents(dbEvents, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                eventCreatedCallback.onEventCreatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                String message = (String) object;
                eventCreatedCallback.onEventCreatedSuccess(message);
            }
        });
    }

    @Override
    public void updateEvent(DbEvent dbEvent, int store, EventUpdatedCallback eventUpdatedCallback) {
        final EventDataStore eventDataStore = eventDataStoreFactory.create(store);
        eventDataStore.updateEvent(dbEvent, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                eventUpdatedCallback.onEventUpdatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                String message = (String) object;
                eventUpdatedCallback.onEventUpdatedSuccess(message);
            }
        });
    }
}
