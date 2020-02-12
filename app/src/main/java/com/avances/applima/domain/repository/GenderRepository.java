package com.avances.applima.domain.repository;

import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.data.datasource.db.model.DbGender;
import com.avances.applima.interactor.country.CountryCreatedCallback;
import com.avances.applima.interactor.country.CountryListCallback;
import com.avances.applima.interactor.gender.GenderCreatedCallback;
import com.avances.applima.interactor.gender.GenderListCallback;

import java.util.List;

public interface GenderRepository {

    void getGenders(int store, GenderListCallback genderListCallback);

    void createGender(List<DbGender> dbGenders, int store, GenderCreatedCallback genderCreatedCallback);

}
