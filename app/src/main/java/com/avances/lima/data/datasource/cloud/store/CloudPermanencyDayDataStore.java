package com.avances.lima.data.datasource.cloud.store;

import com.avances.lima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.lima.data.datasource.datastore.CountryDataStore;
import com.avances.lima.data.datasource.datastore.PermanencyDayDataStore;
import com.avances.lima.data.datasource.db.model.DbCountry;
import com.avances.lima.data.datasource.db.model.DbPermanencyDay;
import com.avances.lima.domain.model.PermanencyDay;
import com.avances.lima.domain.repository.RepositoryCallback;

import java.util.List;

public class CloudPermanencyDayDataStore implements PermanencyDayDataStore {
    private static final String TAG = "CloudCountryDataStore";

    private ApiInterface apiInterface;

    public CloudPermanencyDayDataStore() {
       // apiInterface = ApiClient.getApiClient();
    }


    @Override
    public void getPermanencyDays(RepositoryCallback repositoryCallback) {

    }

    @Override
    public void createPermanencyDay(List<DbPermanencyDay> dbPermanencyDays, RepositoryCallback repositoryCallback) {

    }
}
