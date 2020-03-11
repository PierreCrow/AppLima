package com.avances.lima.domain.repository;

import com.avances.lima.data.datasource.db.model.DbPermanencyDay;
import com.avances.lima.interactor.permanencyday.PermanencyDayCreatedCallback;
import com.avances.lima.interactor.permanencyday.PermanencyDayListCallback;

import java.util.List;

public interface PermanencyDayRepository {

    void getPermanencyDays(int store, PermanencyDayListCallback permanencyDayListCallback);

    void createPermanencyDay(List<DbPermanencyDay> dbPermanencyDays, int store, PermanencyDayCreatedCallback permanencyDayCreatedCallback);

}
