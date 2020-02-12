package com.avances.applima.interactor.gender;

import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.data.datasource.db.model.DbGender;
import com.avances.applima.domain.repository.CountryRepository;
import com.avances.applima.domain.repository.GenderRepository;
import com.avances.applima.interactor.country.CountryCreatedCallback;
import com.avances.applima.interactor.country.CountryListCallback;

import java.util.List;

public class GenderInteractor {

    private final GenderRepository genderRepository;

    public GenderInteractor(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public void getGenders(int store, GenderListCallback countryListCallback) {
        genderRepository.getGenders(store, countryListCallback);
    }

    public void createGender(List<DbGender> dbGenders, int store, GenderCreatedCallback countryCreatedCallback) {
        genderRepository.createGender(dbGenders,store, countryCreatedCallback);
    }

}
