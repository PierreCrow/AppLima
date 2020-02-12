package com.avances.applima.data.datasource.datastore;


import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.domain.repository.RepositoryCallback;

import java.util.List;

public interface CountryDataStore {

    void getCountries(RepositoryCallback repositoryCallback);

    void createCountry(List<DbCountry> dbInterests, RepositoryCallback repositoryCallback);

}
