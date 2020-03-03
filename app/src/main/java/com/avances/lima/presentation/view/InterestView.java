package com.avances.lima.presentation.view;

import com.avances.lima.domain.model.Interest;

import java.util.List;

public interface InterestView extends BaseView {

    void interestListLoaded(List<Interest> dbInterests);

    void interestCreated(String message);

    void interestUpdated(String message);

    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);
}
