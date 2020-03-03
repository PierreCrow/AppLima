package com.avances.lima.domain.repository;

import com.avances.lima.data.datasource.db.model.DbEvent;
import com.avances.lima.interactor.event.EventCreatedCallback;
import com.avances.lima.interactor.event.EventListCallback;
import com.avances.lima.interactor.event.EventUpdatedCallback;

import java.util.List;

public interface EventRepository {

    void getEvents(int store, EventListCallback eventListCallback);

    void createEvents(List<DbEvent> dbEvents, int store, EventCreatedCallback eventCreatedCallback);

    void updateEvent(DbEvent dbEvent, int store, EventUpdatedCallback eventUpdatedCallback);
}
