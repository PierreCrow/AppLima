package com.avances.applima.presentation.view;

import com.avances.applima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.applima.data.datasource.db.model.DbInterest;
import com.avances.applima.domain.model.Interest;

import java.util.ArrayList;
import java.util.List;

public interface InterestView extends BaseView {

    void interestListLoaded(List<Interest> dbInterests);

    void interestCreated(String message);

    void interestUpdated(String message);

    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);
}
