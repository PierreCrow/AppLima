package com.avances.lima.data.datasource.cloud.store;

import com.avances.lima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.lima.data.datasource.datastore.EventDataStore;
import com.avances.lima.data.datasource.db.model.DbEvent;
import com.avances.lima.domain.repository.RepositoryCallback;

import java.util.List;

public class CloudEventDataStore implements EventDataStore {
    private static final String TAG = "CloudPlaceDataStore";

    private ApiInterface apiInterface;

    public CloudEventDataStore() {
      //  apiInterface = ApiClient.getApiClient();
    }


    @Override
    public void getEvents(RepositoryCallback repositoryCallback) {

    }

    @Override
    public void createEvents(List<DbEvent> dbEvents, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void updateEvent(DbEvent dbEvent, RepositoryCallback repositoryCallback) {

    }
}
