package com.avances.applima.data.datasource.cloud.store;

import com.avances.applima.data.datasource.cloud.apiclient.ApiClient;
import com.avances.applima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.applima.data.datasource.datastore.PlaceDataStore;
import com.avances.applima.data.datasource.datastore.RouteDataStore;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.data.datasource.db.model.DbRoute;
import com.avances.applima.domain.repository.RepositoryCallback;

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
