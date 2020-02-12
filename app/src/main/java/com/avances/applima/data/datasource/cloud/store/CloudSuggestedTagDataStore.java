package com.avances.applima.data.datasource.cloud.store;

import com.avances.applima.data.datasource.cloud.apiclient.ApiClient;
import com.avances.applima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.applima.data.datasource.datastore.CountryDataStore;
import com.avances.applima.data.datasource.datastore.SuggestedTagDataStore;
import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.data.datasource.db.model.DbSuggestedTag;
import com.avances.applima.domain.repository.RepositoryCallback;

import java.util.List;

public class CloudSuggestedTagDataStore implements SuggestedTagDataStore {
    private static final String TAG = "CloudSuggestedTagDataStore";

    private ApiInterface apiInterface;

    public CloudSuggestedTagDataStore() {
       // apiInterface = ApiClient.getApiClient("");
    }




    @Override
    public void getSuggestedTags(RepositoryCallback repositoryCallback) {

    }

    @Override
    public void createSuggestedTag(List<DbSuggestedTag> dbSuggestedTags, RepositoryCallback repositoryCallback) {

    }
}
