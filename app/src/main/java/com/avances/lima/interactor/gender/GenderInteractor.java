package com.avances.lima.interactor.gender;

import com.avances.lima.data.datasource.db.model.DbGender;
import com.avances.lima.domain.repository.GenderRepository;

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
