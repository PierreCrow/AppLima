package com.avances.lima.data.datasource.datastore;


import com.avances.lima.data.datasource.db.model.DbEvent;

import com.avances.lima.domain.repository.RepositoryCallback;

import java.util.List;

public interface EventDataStore {

    void getEvents(RepositoryCallback repositoryCallback);

    void createEvents(List<DbEvent> dbEvents, RepositoryCallback repositoryCallback);

    void updateEvent(DbEvent dbEvent, RepositoryCallback repositoryCallback);

}
