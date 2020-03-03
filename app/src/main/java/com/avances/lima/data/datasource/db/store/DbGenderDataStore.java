package com.avances.lima.data.datasource.db.store;

import android.content.Context;

import com.avances.lima.data.datasource.datastore.GenderDataStore;
import com.avances.lima.data.datasource.db.AppLimaDb;
import com.avances.lima.data.datasource.db.dao.GenderDao;
import com.avances.lima.data.datasource.db.model.DbGender;
import com.avances.lima.data.mapper.GenderDataMapper;
import com.avances.lima.domain.model.Gender;
import com.avances.lima.domain.repository.RepositoryCallback;

import java.util.List;

public class DbGenderDataStore implements GenderDataStore {

    GenderDao genderDao;

    public DbGenderDataStore(Context context) {
        genderDao = AppLimaDb.getDatabase(context).genderDao();
    }




    @Override
    public void getGenders(RepositoryCallback repositoryCallback) {
        try {
            GenderDataMapper genderDataMapper = new GenderDataMapper();

            List<DbGender> dbCountries = genderDao.GetAll();
            List<Gender> genders = genderDataMapper.transformDbToEntity(dbCountries);
            repositoryCallback.onSuccess(genders);

        } catch (Exception ex) {
            repositoryCallback.onError("Error");
        }
    }

    @Override
    public void createGender(List<DbGender> dbGenders, RepositoryCallback repositoryCallback) {
        if (dbGenders.size() > 0) {
            try {
                genderDao.InsertMultiple(dbGenders);
                repositoryCallback.onSuccess("Success");

            } catch (Exception ex) {
                repositoryCallback.onError("Error");
            }
        }
    }
}
