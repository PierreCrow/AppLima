package com.avances.applima.domain.repository;

import com.avances.applima.data.datasource.db.model.DbEvent;
import com.avances.applima.interactor.event.EventCreatedCallback;
import com.avances.applima.interactor.event.EventListCallback;
import com.avances.applima.interactor.event.EventUpdatedCallback;

import java.util.List;

public interface EventRepository {

    void getEvents(int store, EventListCallback eventListCallback);

    void createEvents(List<DbEvent> dbEvents, int store, EventCreatedCallback eventCreatedCallback);

    void updateEvent(DbEvent dbEvent, int store, EventUpdatedCallback eventUpdatedCallback);
}
