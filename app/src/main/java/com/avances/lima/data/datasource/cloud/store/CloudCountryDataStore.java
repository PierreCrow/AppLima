package com.avances.lima.data.datasource.cloud.store;

import com.avances.lima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.lima.data.datasource.datastore.CountryDataStore;
import com.avances.lima.data.datasource.db.model.DbCountry;
import com.avances.lima.domain.repository.RepositoryCallback;

import java.util.List;

public class CloudCountryDataStore implements CountryDataStore {
    private static final String TAG = "CloudCountryDataStore";

    private ApiInterface apiInterface;

    public CloudCountryDataStore() {
       // apiInterface = ApiClient.getApiClient();
    }


    @Override
    public void getCountries(RepositoryCallback repositoryCallback) {

    }

    @Override
    public void createCountry(List<DbCountry> dbInterests, RepositoryCallback repositoryCallback) {

    }
}
