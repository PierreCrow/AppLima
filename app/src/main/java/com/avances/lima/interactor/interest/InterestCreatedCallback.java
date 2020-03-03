package com.avances.lima.interactor.interest;

public interface InterestCreatedCallback {

    void onInterestCreatedSuccess(String message);

    void onInterestCreatedError(String message);
}
