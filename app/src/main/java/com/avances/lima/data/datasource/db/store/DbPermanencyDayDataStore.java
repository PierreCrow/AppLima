package com.avances.lima.data.datasource.db.store;

import android.content.Context;

import com.avances.lima.data.datasource.datastore.PermanencyDayDataStore;
import com.avances.lima.data.datasource.db.AppLimaDb;
import com.avances.lima.data.datasource.db.dao.PermanencyDayDao;
import com.avances.lima.data.datasource.db.model.DbPermanencyDay;
import com.avances.lima.data.mapper.PermanencyDayDataMapper;
import com.avances.lima.domain.model.PermanencyDay;
import com.avances.lima.domain.repository.RepositoryCallback;

import java.util.List;

public class DbPermanencyDayDataStore implements PermanencyDayDataStore {

    PermanencyDayDao permanencyDayDao;

    public DbPermanencyDayDataStore(Context context) {
        permanencyDayDao = AppLimaDb.getDatabase(context).permanencyDayDao();
    }

    @Override
    public void getPermanencyDays(RepositoryCallback repositoryCallback) {
        try {
            PermanencyDayDataMapper permanencyDayDataMapper = new PermanencyDayDataMapper();

            List<DbPermanencyDay> dbPermanencyDays = permanencyDayDao.GetAll();
            List<PermanencyDay> permanencyDays = permanencyDayDataMapper.transformDbToEntity(dbPermanencyDays);
            repositoryCallback.onSuccess(permanencyDays);

        } catch (Exception ex) {
            repositoryCallback.onError("Error");
        }
    }

    @Override
    public void createPermanencyDay(List<DbPermanencyDay> dbPermanencyDays, RepositoryCallback repositoryCallback) {
        if (dbPermanencyDays.size() > 0) {
            try {
                permanencyDayDao.InsertMultiple(dbPermanencyDays);
                repositoryCallback.onSuccess("Success");

            } catch (Exception ex) {
                repositoryCallback.onError("Error");
            }
        }
    }
}
