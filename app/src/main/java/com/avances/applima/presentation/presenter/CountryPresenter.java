package com.avances.applima.presentation.presenter;

import com.avances.applima.data.datasource.datastore.CountryDataStoreFactory;
import com.avances.applima.data.datasource.datastore.InterestDataStoreFactory;
import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.data.datasource.db.model.DbInterest;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.data.repository.CountryDataRepository;
import com.avances.applima.data.repository.InterestDataRepository;
import com.avances.applima.domain.model.Country;
import com.avances.applima.domain.model.Interest;
import com.avances.applima.domain.repository.CountryRepository;
import com.avances.applima.domain.repository.InterestRepository;
import com.avances.applima.interactor.country.CountryCreatedCallback;
import com.avances.applima.interactor.country.CountryInteractor;
import com.avances.applima.interactor.country.CountryListCallback;
import com.avances.applima.interactor.interest.InterestInteractor;
import com.avances.applima.presentation.view.CountryView;
import com.avances.applima.presentation.view.InterestView;

import java.util.List;

public class CountryPresenter implements Presenter<CountryView>, CountryListCallback,
        CountryCreatedCallback {

    private CountryView countryView;
    private CountryInteractor countryInteractor;


    public void getCountries(int store) {
        countryInteractor.getCountries(store, this);
    }

    public void createCountry(List<DbCountry> dbCountries, int store) {
        countryInteractor.createCountry(dbCountries, store, this);
    }




    @Override
    public void onCountryCreatedSuccess(String message) {
        countryView.countryCreated(message);
    }

    @Override
    public void onCountryCreatedError(String message) {
        countryView.showErrorMessage(message);
    }

    @Override
    public void onGetCountryListSuccess(List<Country> countries) {
        countryView.countryListLoaded(countries);
    }

    @Override
    public void onGetCountryListError(String message) {
        countryView.showErrorMessage(message);
    }

    @Override
    public void addView(CountryView view) {
        this.countryView = view;
        CountryRepository placeRepository = new CountryDataRepository(new CountryDataStoreFactory(this.countryView.getContext()));
        countryInteractor = new CountryInteractor(placeRepository);
    }

    @Override
    public void removeView(CountryView view) {

    }
}
