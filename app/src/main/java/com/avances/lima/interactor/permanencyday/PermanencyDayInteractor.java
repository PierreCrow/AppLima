package com.avances.lima.interactor.permanencyday;

import com.avances.lima.data.datasource.db.model.DbPermanencyDay;
import com.avances.lima.domain.repository.PermanencyDayRepository;

import java.util.List;

public class PermanencyDayInteractor {

    private final PermanencyDayRepository permanencyDayRepository;

    public PermanencyDayInteractor(PermanencyDayRepository permanencyDayRepository) {
        this.permanencyDayRepository = permanencyDayRepository;
    }

    public void getPermanencyDays(int store, PermanencyDayListCallback permanencyDayListCallback) {
        permanencyDayRepository.getPermanencyDays(store, permanencyDayListCallback);
    }

    public void createPermanencyDay(List<DbPermanencyDay> dbPermanencyDays, int store, PermanencyDayCreatedCallback permanencyDayCreatedCallback) {
        permanencyDayRepository.createPermanencyDay(dbPermanencyDays, store, permanencyDayCreatedCallback);
    }

}
