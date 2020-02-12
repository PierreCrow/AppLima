package com.avances.applima.interactor.interest;

import com.avances.applima.domain.model.Interest;
import com.avances.applima.domain.model.Place;

import java.util.List;

public interface InterestListCallback {

    void onGetInterestListSuccess(List<Interest> places);
    void onGetInterestListError(String message);
}
