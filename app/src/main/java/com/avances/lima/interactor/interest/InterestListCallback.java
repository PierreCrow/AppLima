package com.avances.lima.interactor.interest;

import com.avances.lima.domain.model.Interest;

import java.util.List;

public interface InterestListCallback {

    void onGetInterestListSuccess(List<Interest> places);
    void onGetInterestListError(String message);
}
