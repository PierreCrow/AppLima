package com.avances.lima.data.datasource.datastore;


import com.avances.lima.data.datasource.db.model.DbCountry;
import com.avances.lima.domain.repository.RepositoryCallback;

import java.util.List;

public interface CountryDataStore {

    void getCountries(RepositoryCallback repositoryCallback);

    void createCountry(List<DbCountry> dbInterests, RepositoryCallback repositoryCallback);

}
