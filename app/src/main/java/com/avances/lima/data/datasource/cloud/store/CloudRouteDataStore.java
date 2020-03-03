package com.avances.lima.data.datasource.cloud.store;

import com.avances.lima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.lima.data.datasource.datastore.RouteDataStore;
import com.avances.lima.data.datasource.db.model.DbRoute;
import com.avances.lima.domain.repository.RepositoryCallback;

import java.util.List;

public class CloudRouteDataStore implements RouteDataStore {
    private static final String TAG = "CloudPlaceDataStore";

    private ApiInterface apiInterface;

    public CloudRouteDataStore() {
      //  apiInterface = ApiClient.getApiClient();
    }


    @Override
    public void getRoutes(RepositoryCallback repositoryCallback) {

    }

    @Override
    public void createRoutes(List<DbRoute> dbRoutes, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void updateRoute(DbRoute dbRoute, RepositoryCallback repositoryCallback) {

    }
}
