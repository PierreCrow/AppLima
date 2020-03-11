package com.avances.lima.presentation.view;

import com.avances.lima.domain.model.PermanencyDay;

import java.util.List;

public interface PermanencyDayView extends BaseView {

    void permanencyDayListLoaded(List<PermanencyDay> permanencyDays);

    void permanencyDayCreated(String message);


    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);
}
