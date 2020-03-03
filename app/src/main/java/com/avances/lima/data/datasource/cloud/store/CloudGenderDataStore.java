package com.avances.lima.data.datasource.cloud.store;

import com.avances.lima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.lima.data.datasource.datastore.GenderDataStore;
import com.avances.lima.data.datasource.db.model.DbGender;
import com.avances.lima.domain.repository.RepositoryCallback;

import java.util.List;

public class CloudGenderDataStore implements GenderDataStore {
    private static final String TAG = "CloudGenderDataStore";

    private ApiInterface apiInterface;

    public CloudGenderDataStore() {
      //  apiInterface = ApiClient.getApiClient();
    }


    @Override
    public void getGenders(RepositoryCallback repositoryCallback) {

    }

    @Override
    public void createGender(List<DbGender> dbGenders, RepositoryCallback repositoryCallback) {

    }
}
