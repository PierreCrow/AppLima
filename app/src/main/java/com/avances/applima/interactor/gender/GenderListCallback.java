package com.avances.applima.interactor.gender;

import com.avances.applima.domain.model.Country;
import com.avances.applima.domain.model.Gender;

import java.util.List;

public interface GenderListCallback {

    void onGetGenderListSuccess(List<Gender> genders);
    void onGetGenderListError(String message);
}
