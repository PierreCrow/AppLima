package com.avances.applima.data.datasource.cloud.store;

import com.avances.applima.data.datasource.cloud.apiclient.ApiClient;
import com.avances.applima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.applima.data.datasource.datastore.DistritNeighborhoodDataStore;
import com.avances.applima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.applima.domain.repository.RepositoryCallback;

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
