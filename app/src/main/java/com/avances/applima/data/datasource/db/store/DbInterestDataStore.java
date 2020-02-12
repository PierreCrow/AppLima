package com.avances.applima.data.datasource.db.store;

import android.content.Context;

import com.avances.applima.data.datasource.datastore.InterestDataStore;
import com.avances.applima.data.datasource.db.AppLimaDb;
import com.avances.applima.data.datasource.db.dao.InterestDao;
import com.avances.applima.data.datasource.db.model.DbInterest;
import com.avances.applima.data.mapper.InterestDataMapper;
import com.avances.applima.domain.model.Interest;
import com.avances.applima.domain.repository.RepositoryCallback;

import java.util.List;

public class DbInterestDataStore implements InterestDataStore {

    InterestDao interestDao;

    public DbInterestDataStore(Context context) {
        interestDao = AppLimaDb.getDatabase(context).interestDao();
    }


    @Override
    public void getInterests(RepositoryCallback repositoryCallback) {

        try {
            InterestDataMapper interestDataMapper = new InterestDataMapper();

            List<DbInterest> dbInterests = interestDao.GetAll();
            List<Interest> interests = interestDataMapper.transformDbToEntity(dbInterests);
            repositoryCallback.onSuccess(interests);

        } catch (Exception ex) {
            repositoryCallback.onError("Error");
        }
    }

    @Override
    public void createInterests(List<DbInterest> dbInterests, RepositoryCallback repositoryCallback) {

        if (dbInterests.size() > 0) {
            try {
                interestDao.InsertMultiple(dbInterests);
                repositoryCallback.onSuccess("Success");

            } catch (Exception ex) {
                repositoryCallback.onError("Error");
            }
        }
    }

    @Override
    public void updateInterest(DbInterest dbInterest, RepositoryCallback repositoryCallback) {

    }
}
