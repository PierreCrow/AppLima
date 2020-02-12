package com.avances.applima.data.datasource.cloud.store;

import com.avances.applima.data.datasource.cloud.apiclient.ApiClient;
import com.avances.applima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.applima.data.datasource.datastore.CountryDataStore;
import com.avances.applima.data.datasource.datastore.GenderDataStore;
import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.data.datasource.db.model.DbGender;
import com.avances.applima.domain.repository.RepositoryCallback;

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
