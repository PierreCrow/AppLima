package com.avances.lima.interactor.country;

public interface CountryCreatedCallback {

    void onCountryCreatedSuccess(String message);

    void onCountryCreatedError(String message);
}
