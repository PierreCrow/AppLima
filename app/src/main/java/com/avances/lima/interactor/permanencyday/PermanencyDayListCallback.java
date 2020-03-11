package com.avances.lima.interactor.permanencyday;

import com.avances.lima.domain.model.PermanencyDay;

import java.util.List;

public interface PermanencyDayListCallback {

    void onGetPermanencyDayListSuccess(List<PermanencyDay> countries);

    void onGetPermanencyDayListError(String message);
}
