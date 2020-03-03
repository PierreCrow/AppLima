package com.avances.lima.domain.repository;

import com.avances.lima.data.datasource.db.model.DbCountry;
import com.avances.lima.interactor.country.CountryCreatedCallback;
import com.avances.lima.interactor.country.CountryListCallback;

import java.util.List;

public interface CountryRepository {

    void getCountries(int store, CountryListCallback countryListCallback);

    void createCountry(List<DbCountry> dbCountries, int store, CountryCreatedCallback countryCreatedCallback);

}
