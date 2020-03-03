package com.avances.lima.presentation.presenter;

import com.avances.lima.data.datasource.datastore.DistritNeighborhoodDataStoreFactory;
import com.avances.lima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.lima.data.repository.DistritNeighborhoodDataRepository;
import com.avances.lima.domain.model.DistritNeighborhood;
import com.avances.lima.domain.repository.DistritNeighborhoodRepository;
import com.avances.lima.interactor.distritneighborhood.DistritNeighborhoodCreatedCallback;
import com.avances.lima.interactor.distritneighborhood.DistritNeighborhoodInteractor;
import com.avances.lima.interactor.distritneighborhood.DistritNeighborhoodListCallback;
import com.avances.lima.interactor.distritneighborhood.DistritNeighborhoodUpdatedCallback;
import com.avances.lima.presentation.view.DistritNeighborhoodView;

import java.util.List;

public class DistritNeighborhoodPresenter implements Presenter<DistritNeighborhoodView>,
        DistritNeighborhoodListCallback, DistritNeighborhoodCreatedCallback, DistritNeighborhoodUpdatedCallback {

    private DistritNeighborhoodView distritNeighborhoodView;
    private DistritNeighborhoodInteractor distritNeighborhoodInteractor;


    public void getDistritNeighborhoods(int store) {
        distritNeighborhoodInteractor.getDistritNeighborhoods(store, this);
    }

    public void createDistritNeighborhoods(List<DbDistritNeighborhood> dbDistritNeighborhoods, int store) {
        distritNeighborhoodInteractor.createDistritNeighborhoods(dbDistritNeighborhoods, store, this);
    }

    public void updateDistritNeighborhood(DbDistritNeighborhood dbDistritNeighborhood, int store) {
        distritNeighborhoodInteractor.updateDistritNeighborhood(dbDistritNeighborhood, store, this);
    }


    @Override
    public void onGetDistritNeighborhoodListSuccess(List<DistritNeighborhood> dbDistritNeighborhoods) {
        distritNeighborhoodView.distritNeighborhoodListLoaded(dbDistritNeighborhoods);
    }

    @Override
    public void onGetDistritNeighborhoodListError(String message) {
        distritNeighborhoodView.showErrorMessage(message);
    }

    @Override
    public void addView(DistritNeighborhoodView view) {
        this.distritNeighborhoodView = view;
        DistritNeighborhoodRepository distritNeighborhoodRepository = new DistritNeighborhoodDataRepository(new DistritNeighborhoodDataStoreFactory(this.distritNeighborhoodView.getContext()));
        distritNeighborhoodInteractor = new DistritNeighborhoodInteractor(distritNeighborhoodRepository);
    }

    @Override
    public void removeView(DistritNeighborhoodView view) {

    }

    @Override
    public void onDistritNeighborhoodCreatedSuccess(String message) {
        distritNeighborhoodView.distritCreated(message);
    }

    @Override
    public void onDistritNeighborhoodCreatedError(String message) {
        distritNeighborhoodView.showErrorMessage(message);
    }

    @Override
    public void onDistritNeighborhoodUpdatedSuccess(String message) {
        distritNeighborhoodView.distritUpdated(message);
    }

    @Override
    public void onDistritNeighborhoodUpdatedError(String message) {
        distritNeighborhoodView.showErrorMessage(message);
    }
}
