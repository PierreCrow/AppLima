package com.avances.lima.data.datasource.db.store;

import android.content.Context;

import com.avances.lima.data.datasource.datastore.EventDataStore;
import com.avances.lima.data.datasource.db.AppLimaDb;
import com.avances.lima.data.datasource.db.dao.EventDao;
import com.avances.lima.data.datasource.db.model.DbEvent;
import com.avances.lima.data.mapper.EventDataMapper;
import com.avances.lima.domain.model.Event;
import com.avances.lima.domain.repository.RepositoryCallback;

import java.util.List;

public class DbEventDataStore implements EventDataStore {

    EventDao eventDao;

    public DbEventDataStore(Context context) {
        eventDao = AppLimaDb.getDatabase(context).eventDao();
    }


    @Override
    public void getEvents(RepositoryCallback repositoryCallback) {
        try {
            EventDataMapper eventDataMapper= new EventDataMapper();

            List<DbEvent> dbEvents= eventDao.GetAll();
            List<Event> events=eventDataMapper.transformDbToEntity(dbEvents);
            repositoryCallback.onSuccess(events);

        } catch (Exception ex) {
            repositoryCallback.onError("Error");
        }
    }

    @Override
    public void createEvents(List<DbEvent> dbEvents, RepositoryCallback repositoryCallback) {
        if (dbEvents.size() > 0) {
            try {
                eventDao.InsertMultiple(dbEvents);
                repositoryCallback.onSuccess("Success");

            } catch (Exception ex) {
                repositoryCallback.onError("Error");
            }
        }
    }

    @Override
    public void updateEvent(DbEvent dbEvent, RepositoryCallback repositoryCallback) {

    }
}
