package com.avances.lima.data.datasource.cloud.store;

import com.avances.lima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.lima.data.datasource.datastore.PlaceDataStore;
import com.avances.lima.data.datasource.db.model.DbPlace;
import com.avances.lima.domain.model.Place;
import com.avances.lima.domain.repository.RepositoryCallback;

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
