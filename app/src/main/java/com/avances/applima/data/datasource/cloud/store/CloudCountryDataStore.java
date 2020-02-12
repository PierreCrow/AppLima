package com.avances.applima.data.datasource.cloud.store;

import com.avances.applima.data.datasource.cloud.apiclient.ApiClient;
import com.avances.applima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.applima.data.datasource.datastore.CountryDataStore;
import com.avances.applima.data.datasource.datastore.InterestDataStore;
import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.data.datasource.db.model.DbInterest;
import com.avances.applima.domain.repository.RepositoryCallback;

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
