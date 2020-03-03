package com.avances.lima.domain.repository;

import com.avances.lima.data.datasource.db.model.DbGender;
import com.avances.lima.interactor.gender.GenderCreatedCallback;
import com.avances.lima.interactor.gender.GenderListCallback;

import java.util.List;

public interface GenderRepository {

    void getGenders(int store, GenderListCallback genderListCallback);

    void createGender(List<DbGender> dbGenders, int store, GenderCreatedCallback genderCreatedCallback);

}
