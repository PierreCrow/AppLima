package com.avances.lima.interactor.country;

import com.avances.lima.data.datasource.db.model.DbCountry;
import com.avances.lima.domain.repository.CountryRepository;


import java.util.List;

public class CountryInteractor {

    private final CountryRepository countryRepository;

    public CountryInteractor(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void getCountries(int store, CountryListCallback countryListCallback) {
        countryRepository.getCountries(store, countryListCallback);
    }

    public void createCountry(List<DbCountry> dbCountries, int store, CountryCreatedCallback countryCreatedCallback) {
        countryRepository.createCountry(dbCountries,store, countryCreatedCallback);
    }

}
