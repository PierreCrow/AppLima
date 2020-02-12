package com.avances.applima.presentation.presenter;

import com.avances.applima.data.datasource.datastore.CountryDataStoreFactory;
import com.avances.applima.data.datasource.datastore.GenderDataStoreFactory;
import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.data.datasource.db.model.DbGender;
import com.avances.applima.data.repository.CountryDataRepository;
import com.avances.applima.data.repository.GenderDataRepository;
import com.avances.applima.domain.model.Country;
import com.avances.applima.domain.model.Gender;
import com.avances.applima.domain.repository.CountryRepository;
import com.avances.applima.domain.repository.GenderRepository;
import com.avances.applima.interactor.country.CountryInteractor;
import com.avances.applima.interactor.gender.GenderCreatedCallback;
import com.avances.applima.interactor.gender.GenderInteractor;
import com.avances.applima.interactor.gender.GenderListCallback;
import com.avances.applima.presentation.view.CountryView;
import com.avances.applima.presentation.view.GenderView;

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
