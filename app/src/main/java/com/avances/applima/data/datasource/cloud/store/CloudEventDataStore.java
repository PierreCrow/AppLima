package com.avances.applima.data.datasource.cloud.store;

import com.avances.applima.data.datasource.cloud.apiclient.ApiClient;
import com.avances.applima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.applima.data.datasource.datastore.EventDataStore;
import com.avances.applima.data.datasource.datastore.PlaceDataStore;
import com.avances.applima.data.datasource.db.model.DbEvent;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.domain.repository.RepositoryCallback;

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
