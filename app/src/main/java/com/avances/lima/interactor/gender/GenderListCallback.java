package com.avances.lima.interactor.gender;

import com.avances.lima.domain.model.Gender;

import java.util.List;

public interface GenderListCallback {

    void onGetGenderListSuccess(List<Gender> genders);
    void onGetGenderListError(String message);
}
