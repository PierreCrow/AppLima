package com.avances.applima.data.datasource.datastore;


import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.data.datasource.db.model.DbGender;
import com.avances.applima.domain.repository.RepositoryCallback;

import java.util.List;

public interface GenderDataStore {

    void getGenders(RepositoryCallback repositoryCallback);

    void createGender(List<DbGender> dbGenders, RepositoryCallback repositoryCallback);

}
