package com.avances.lima.data.datasource.cloud.store;

import com.avances.lima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.lima.data.datasource.datastore.InterestDataStore;
import com.avances.lima.data.datasource.db.model.DbInterest;
import com.avances.lima.domain.repository.RepositoryCallback;

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
