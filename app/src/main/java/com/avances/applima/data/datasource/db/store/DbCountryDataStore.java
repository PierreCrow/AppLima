package com.avances.applima.data.datasource.db.store;

import android.content.Context;

import com.avances.applima.data.datasource.datastore.CountryDataStore;
import com.avances.applima.data.datasource.db.AppLimaDb;
import com.avances.applima.data.datasource.db.dao.CountryDao;
import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.data.datasource.db.model.DbInterest;
import com.avances.applima.data.mapper.CountryDataMapper;
import com.avances.applima.data.mapper.InterestDataMapper;
import com.avances.applima.domain.model.Country;
import com.avances.applima.domain.model.Interest;
import com.avances.applima.domain.repository.RepositoryCallback;

import java.util.List;

public class DbCountryDataStore implements CountryDataStore {

    CountryDao countryDao;

    public DbCountryDataStore(Context context) {
        countryDao = AppLimaDb.getDatabase(context).countryDao();
    }



    @Override
    public void getCountries(RepositoryCallback repositoryCallback) {
        try {
            CountryDataMapper countryDataMapper = new CountryDataMapper();

            List<DbCountry> dbCountries = countryDao.GetAll();
            List<Country> interests = countryDataMapper.transformDbToEntity(dbCountries);
            repositoryCallback.onSuccess(interests);

        } catch (Exception ex) {
            repositoryCallback.onError("Error");
        }
    }

    @Override
    public void createCountry(List<DbCountry> dbCountries, RepositoryCallback repositoryCallback) {
        if (dbCountries.size() > 0) {
            try {
                countryDao.InsertMultiple(dbCountries);
                repositoryCallback.onSuccess("Success");

            } catch (Exception ex) {
                repositoryCallback.onError("Error");
            }
        }
    }
}
