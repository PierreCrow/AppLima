package com.avances.lima.data.datasource.datastore;

import com.avances.lima.data.datasource.db.model.DbPermanencyDay;
import com.avances.lima.domain.repository.RepositoryCallback;

import java.util.List;

public interface PermanencyDayDataStore {

    void getPermanencyDays(RepositoryCallback repositoryCallback);

    void createPermanencyDay(List<DbPermanencyDay> dbPermanencyDays, RepositoryCallback repositoryCallback);

}
