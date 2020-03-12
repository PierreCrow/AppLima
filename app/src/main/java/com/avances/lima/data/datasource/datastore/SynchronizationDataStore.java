package com.avances.lima.data.datasource.datastore;

import com.avances.lima.domain.repository.RepositoryCallback;

public interface SynchronizationDataStore {

    void syncAll(String token,RepositoryCallback repositoryCallback);

    void verifySynchronization(String token,String lastDateSync,RepositoryCallback repositoryCallback);

}
