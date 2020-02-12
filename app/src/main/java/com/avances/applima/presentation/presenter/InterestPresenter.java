package com.avances.applima.presentation.presenter;

import com.avances.applima.data.datasource.datastore.InterestDataStoreFactory;
import com.avances.applima.data.datasource.datastore.PlaceDataStoreFactory;
import com.avances.applima.data.datasource.db.model.DbInterest;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.data.repository.InterestDataRepository;
import com.avances.applima.data.repository.PlaceDataRepository;
import com.avances.applima.domain.model.Interest;
import com.avances.applima.domain.model.Place;
import com.avances.applima.domain.repository.InterestRepository;
import com.avances.applima.domain.repository.PlaceRepository;
import com.avances.applima.interactor.interest.InterestCreatedCallback;
import com.avances.applima.interactor.interest.InterestInteractor;
import com.avances.applima.interactor.interest.InterestListCallback;
import com.avances.applima.interactor.place.PlaceInteractor;
import com.avances.applima.presentation.view.InterestView;
import com.avances.applima.presentation.view.PlaceView;

import java.util.List;

public class InterestPresenter implements Presenter<InterestView>, InterestListCallback,
        InterestCreatedCallback {

    private InterestView interestView;
    private InterestInteractor interestInteractor;


    public void getInterests(int store) {
        interestInteractor.getInterests(store, this);
    }

    public void createInterests(List<DbInterest> dbInterests, int store) {
        interestInteractor.createInterests(dbInterests, store, this);
    }

    public void updateInterest(DbPlace dbPlace, int store) {
      //  interestInteractor.updatePlaceFavorite(dbPlace, store, this);
    }



    @Override
    public void onInterestCreatedSuccess(String message) {
        interestView.interestCreated(message);
    }

    @Override
    public void onInterestCreatedError(String message) {
        interestView.showErrorMessage(message);
    }

    @Override
    public void onGetInterestListSuccess(List<Interest> interests) {
        interestView.interestListLoaded(interests);
    }

    @Override
    public void onGetInterestListError(String message) {
        interestView.showErrorMessage(message);
    }

    @Override
    public void addView(InterestView view) {
        this.interestView = view;
        InterestRepository placeRepository = new InterestDataRepository(new InterestDataStoreFactory(this.interestView.getContext()));
        interestInteractor = new InterestInteractor(placeRepository);
    }

    @Override
    public void removeView(InterestView view) {

    }
}
