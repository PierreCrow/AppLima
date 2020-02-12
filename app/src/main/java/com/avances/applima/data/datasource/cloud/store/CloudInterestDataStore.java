package com.avances.applima.data.datasource.cloud.store;

import com.avances.applima.data.datasource.cloud.apiclient.ApiClient;
import com.avances.applima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.applima.data.datasource.datastore.InterestDataStore;
import com.avances.applima.data.datasource.datastore.PlaceDataStore;
import com.avances.applima.data.datasource.db.model.DbInterest;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.domain.repository.RepositoryCallback;

import java.util.List;

public class CloudInterestDataStore implements InterestDataStore {
    private static final String TAG = "CloudPlaceDataStore";

    private ApiInterface apiInterface;

    public CloudInterestDataStore() {
      //  apiInterface = ApiClient.getApiClient();
    }


    @Override
    public void getInterests(RepositoryCallback repositoryCallback) {

    }

    @Override
    public void createInterests(List<DbInterest> dbInterests, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void updateInterest(DbInterest dbInterest, RepositoryCallback repositoryCallback) {

    }
}
