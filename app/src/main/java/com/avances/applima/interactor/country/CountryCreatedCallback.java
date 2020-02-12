package com.avances.applima.interactor.country;

public interface CountryCreatedCallback {

    void onCountryCreatedSuccess(String message);

    void onCountryCreatedError(String message);
}
