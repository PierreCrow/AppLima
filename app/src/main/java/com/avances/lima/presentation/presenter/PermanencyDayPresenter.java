package com.avances.lima.presentation.presenter;

import com.avances.lima.data.datasource.datastore.PermanencyDayDataStoreFactory;
import com.avances.lima.data.datasource.db.model.DbPermanencyDay;
import com.avances.lima.data.repository.PermanencyDayDataRepository;
import com.avances.lima.domain.model.PermanencyDay;
import com.avances.lima.domain.repository.PermanencyDayRepository;
import com.avances.lima.interactor.permanencyday.PermanencyDayCreatedCallback;
import com.avances.lima.interactor.permanencyday.PermanencyDayInteractor;
import com.avances.lima.interactor.permanencyday.PermanencyDayListCallback;
import com.avances.lima.presentation.view.PermanencyDayView;

import java.util.List;

public class PermanencyDayPresenter implements Presenter<PermanencyDayView>, PermanencyDayListCallback,
        PermanencyDayCreatedCallback {

    private PermanencyDayView permanencyDayView;
    private PermanencyDayInteractor permanencyDayInteractor;


    public void getPermanencyDays(int store) {
        permanencyDayInteractor.getPermanencyDays(store, this);
    }

    public void createPermanencyDay(List<DbPermanencyDay> dbPermanencyDays, int store) {
        permanencyDayInteractor.createPermanencyDay(dbPermanencyDays, store, this);
    }


    @Override
    public void onPermanencyDayCreatedSuccess(String message) {
        permanencyDayView.permanencyDayCreated(message);
    }

    @Override
    public void onPermanencyDayCreatedError(String message) {
        permanencyDayView.showErrorMessage(message);
    }

    @Override
    public void onGetPermanencyDayListSuccess(List<PermanencyDay> countries) {
        permanencyDayView.permanencyDayListLoaded(countries);
    }

    @Override
    public void onGetPermanencyDayListError(String message) {
        permanencyDayView.showErrorMessage(message);
    }

    @Override
    public void addView(PermanencyDayView view) {
        this.permanencyDayView = view;
        PermanencyDayRepository permanencyDayRepository = new PermanencyDayDataRepository(new PermanencyDayDataStoreFactory(this.permanencyDayView.getContext()));
        permanencyDayInteractor = new PermanencyDayInteractor(permanencyDayRepository);
    }

    @Override
    public void removeView(PermanencyDayView view) {

    }
}
