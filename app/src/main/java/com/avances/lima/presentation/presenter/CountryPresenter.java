package com.avances.lima.presentation.presenter;

import com.avances.lima.data.datasource.datastore.CountryDataStoreFactory;
import com.avances.lima.data.datasource.db.model.DbCountry;
import com.avances.lima.data.repository.CountryDataRepository;
import com.avances.lima.domain.model.Country;
import com.avances.lima.domain.repository.CountryRepository;
import com.avances.lima.interactor.country.CountryCreatedCallback;
import com.avances.lima.interactor.country.CountryInteractor;
import com.avances.lima.interactor.country.CountryListCallback;
import com.avances.lima.presentation.view.CountryView;

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
