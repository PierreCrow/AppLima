package com.avances.lima.data.datasource.cloud.store;

import com.avances.lima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.lima.data.datasource.datastore.DistritNeighborhoodDataStore;
import com.avances.lima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.lima.domain.repository.RepositoryCallback;

import java.util.List;

public class CloudDistritNeighborhoodDataStore implements DistritNeighborhoodDataStore {
    private static final String TAG = "CloudDistritNeighborhoodDataStore";

    private ApiInterface apiInterface;

    public CloudDistritNeighborhoodDataStore() {
     //   apiInterface = ApiClient.getApiClient();
    }


    @Override
    public void getDistritNeighborhoods(RepositoryCallback repositoryCallback) {

    }

    @Override
    public void createDistritNeighborhood(List<DbDistritNeighborhood> dbDistritNeighborhoods, RepositoryCallback repositoryCallback) {

    }


    @Override
    public void updateDistritNeighborhood(DbDistritNeighborhood dbDistritNeighborhood, RepositoryCallback repositoryCallback) {

    }
}
