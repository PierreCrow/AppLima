package com.avances.applima.data.datasource.datastore;


import com.avances.applima.data.datasource.db.model.DbInterest;
import com.avances.applima.domain.repository.RepositoryCallback;

import java.util.List;

public interface InterestDataStore {

    void getInterests(RepositoryCallback repositoryCallback);

    void createInterests(List<DbInterest> dbInterests, RepositoryCallback repositoryCallback);

    void updateInterest(DbInterest dbInterest, RepositoryCallback repositoryCallback);

}
