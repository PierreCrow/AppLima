package com.avances.lima.data.datasource.cloud.store;

import com.avances.lima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.lima.data.datasource.datastore.SuggestedTagDataStore;
import com.avances.lima.data.datasource.db.model.DbSuggestedTag;
import com.avances.lima.domain.repository.RepositoryCallback;

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
