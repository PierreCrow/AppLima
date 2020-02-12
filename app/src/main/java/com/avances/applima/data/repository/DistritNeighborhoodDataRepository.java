package com.avances.applima.data.repository;

import com.avances.applima.data.datasource.datastore.DistritNeighborhoodDataStore;
import com.avances.applima.data.datasource.datastore.DistritNeighborhoodDataStoreFactory;
import com.avances.applima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.applima.domain.model.DistritNeighborhood;
import com.avances.applima.domain.repository.DistritNeighborhoodRepository;
import com.avances.applima.domain.repository.RepositoryCallback;
import com.avances.applima.interactor.distritneighborhood.DistritNeighborhoodCreatedCallback;
import com.avances.applima.interactor.distritneighborhood.DistritNeighborhoodListCallback;
import com.avances.applima.interactor.distritneighborhood.DistritNeighborhoodUpdatedCallback;

import java.util.ArrayList;
import java.util.List;

public class DistritNeighborhoodDataRepository implements DistritNeighborhoodRepository {

    private final DistritNeighborhoodDataStoreFactory distritNeighborhoodDataStoreFactory;

    public DistritNeighborhoodDataRepository(DistritNeighborhoodDataStoreFactory distritNeighborhoodDataStoreFactory) {
        this.distritNeighborhoodDataStoreFactory = distritNeighborhoodDataStoreFactory;
    }

    @Override
    public void getDistritNeighborhoods(int store, DistritNeighborhoodListCallback distritNeighborhoodListCallback) {

        final DistritNeighborhoodDataStore distritNeighborhoodDataStore = distritNeighborhoodDataStoreFactory.create(store);
        distritNeighborhoodDataStore.getDistritNeighborhoods(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                distritNeighborhoodListCallback.onGetDistritNeighborhoodListError(message);
            }

            @Override
            public void onSuccess(Object object) {

                ArrayList<DistritNeighborhood> distritNeighborhoods = (ArrayList<DistritNeighborhood>) object;
                distritNeighborhoodListCallback.onGetDistritNeighborhoodListSuccess(distritNeighborhoods);
            }
        });
    }

    @Override
    public void createDistritNeighborhood(List<DbDistritNeighborhood> dbDistritNeighborhoods, int store, DistritNeighborhoodCreatedCallback distritNeighborhoodCreatedCallback) {
        final DistritNeighborhoodDataStore distritNeighborhoodDataStore = distritNeighborhoodDataStoreFactory.create(store);
        distritNeighborhoodDataStore.createDistritNeighborhood(dbDistritNeighborhoods,new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                distritNeighborhoodCreatedCallback.onDistritNeighborhoodCreatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                String message = (String) object;
                distritNeighborhoodCreatedCallback.onDistritNeighborhoodCreatedSuccess(message);
            }
        });
    }


    @Override
    public void updateDistritNeighborhood(DbDistritNeighborhood dbDistritNeighborhood, int store, DistritNeighborhoodUpdatedCallback distritNeighborhoodUpdatedCallback) {

        final DistritNeighborhoodDataStore distritNeighborhoodDataStore = distritNeighborhoodDataStoreFactory.create(store);
        distritNeighborhoodDataStore.updateDistritNeighborhood(dbDistritNeighborhood,new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                distritNeighborhoodUpdatedCallback.onDistritNeighborhoodUpdatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                String message = (String) object;
                distritNeighborhoodUpdatedCallback.onDistritNeighborhoodUpdatedSuccess(message);
            }
        });
    }
}
