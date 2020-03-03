package com.avances.lima.presentation.view;

import com.avances.lima.domain.model.Country;

import java.util.List;

public interface CountryView extends BaseView {

    void countryListLoaded(List<Country> countries);

    void countryCreated(String message);


    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);
}
