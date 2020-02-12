package com.avances.applima.domain.repository;

import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.interactor.country.CountryCreatedCallback;
import com.avances.applima.interactor.country.CountryListCallback;

import java.util.List;

public interface CountryRepository {

    void getCountries(int store, CountryListCallback countryListCallback);

    void createCountry(List<DbCountry> dbCountries, int store, CountryCreatedCallback countryCreatedCallback);

}
