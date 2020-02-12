package com.avances.applima.data.datasource.datastore;


import com.avances.applima.data.datasource.db.model.DbEvent;

import com.avances.applima.domain.repository.RepositoryCallback;

import java.util.List;

public interface EventDataStore {

    void getEvents(RepositoryCallback repositoryCallback);

    void createEvents(List<DbEvent> dbEvents, RepositoryCallback repositoryCallback);

    void updateEvent(DbEvent dbEvent, RepositoryCallback repositoryCallback);

}
