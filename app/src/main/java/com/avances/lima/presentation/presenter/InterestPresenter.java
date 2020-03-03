package com.avances.lima.presentation.presenter;

import com.avances.lima.data.datasource.datastore.InterestDataStoreFactory;
import com.avances.lima.data.datasource.db.model.DbInterest;
import com.avances.lima.data.datasource.db.model.DbPlace;
import com.avances.lima.data.repository.InterestDataRepository;
import com.avances.lima.domain.model.Interest;
import com.avances.lima.domain.repository.InterestRepository;
import com.avances.lima.interactor.interest.InterestCreatedCallback;
import com.avances.lima.interactor.interest.InterestInteractor;
import com.avances.lima.interactor.interest.InterestListCallback;
import com.avances.lima.presentation.view.InterestView;

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
