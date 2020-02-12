package com.avances.applima.data.datasource.cloud.store;

import com.avances.applima.data.datasource.cloud.apiclient.ApiClient;
import com.avances.applima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.applima.data.datasource.datastore.PlaceDataStore;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.domain.model.Place;
import com.avances.applima.domain.repository.RepositoryCallback;

import java.util.List;

public class CloudPlaceDataStore implements PlaceDataStore {
    private static final String TAG = "CloudPlaceDataStore";

    private ApiInterface apiInterface;

    public CloudPlaceDataStore() {
     //   apiInterface = ApiClient.getApiClient();
    }


    @Override
    public void getPlaces(RepositoryCallback repositoryCallback) {

    }

    @Override
    public void createPlaces(List<DbPlace> dbPlaces, RepositoryCallback repositoryCallback) {

    }


    @Override
    public void updatePlace(Place place,boolean favorite, RepositoryCallback repositoryCallback) {

    }
}
