package com.avances.applima.data.datasource.datastore;

import com.avances.applima.domain.repository.RepositoryCallback;

public interface SynchronizationDataStore {

    void syncAll(String token,RepositoryCallback repositoryCallback);

}
