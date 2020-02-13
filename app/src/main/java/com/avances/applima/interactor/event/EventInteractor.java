package com.avances.applima.interactor.event;

import com.avances.applima.data.datasource.db.model.DbEvent;
import com.avances.applima.domain.repository.EventRepository;


import java.util.List;

public class EventInteractor {

    private final EventRepository eventRepository;

    public EventInteractor(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void getEvents(int store, EventListCallback eventListCallback) {
        eventRepository.getEvents(store, eventListCallback);
    }

    public void createEvents(List<DbEvent> dbEvents, int store, EventCreatedCallback eventCreatedCallback) {
        eventRepository.createEvents(dbEvents, store, eventCreatedCallback);
    }

    public void updateEvent(DbEvent dbEvent, int store, EventUpdatedCallback eventUpdatedCallback) {
        eventRepository.updateEvent(dbEvent, store, eventUpdatedCallback);
    }

}