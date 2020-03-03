package com.avances.lima.presentation.presenter;

import com.avances.lima.data.datasource.datastore.GenderDataStoreFactory;
import com.avances.lima.data.datasource.db.model.DbGender;
import com.avances.lima.data.repository.GenderDataRepository;
import com.avances.lima.domain.model.Gender;
import com.avances.lima.domain.repository.GenderRepository;
import com.avances.lima.interactor.gender.GenderCreatedCallback;
import com.avances.lima.interactor.gender.GenderInteractor;
import com.avances.lima.interactor.gender.GenderListCallback;
import com.avances.lima.presentation.view.GenderView;

import java.util.List;

public class GenderPresenter implements Presenter<GenderView>, GenderListCallback,
        GenderCreatedCallback {

    private GenderView genderView;
    private GenderInteractor genderInteractor;


    public void getGenders(int store) {
        genderInteractor.getGenders(store, this);
    }

    public void createGender(List<DbGender> dbGenders, int store) {
        genderInteractor.createGender(dbGenders, store, this);
    }


    @Override
    public void onGenderCreatedSuccess(String message) {
        genderView.genderCreated(message);
    }

    @Override
    public void onGenderCreatedError(String message) {
        genderView.showErrorMessage(message);
    }

    @Override
    public void onGetGenderListSuccess(List<Gender> genders) {
        genderView.genderListLoaded(genders);
    }

    @Override
    public void onGetGenderListError(String message) {
        genderView.showErrorMessage(message);
    }

    @Override
    public void addView(GenderView view) {
        this.genderView = view;
        GenderRepository placeRepository = new GenderDataRepository(new GenderDataStoreFactory(this.genderView.getContext()));
        genderInteractor = new GenderInteractor(placeRepository);
    }

    @Override
    public void removeView(GenderView view) {

    }
}
