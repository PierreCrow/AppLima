package com.avances.lima.data.datasource.datastore;


import com.avances.lima.data.datasource.db.model.DbGender;
import com.avances.lima.domain.repository.RepositoryCallback;

import java.util.List;

public interface GenderDataStore {

    void getGenders(RepositoryCallback repositoryCallback);

    void createGender(List<DbGender> dbGenders, RepositoryCallback repositoryCallback);

}
