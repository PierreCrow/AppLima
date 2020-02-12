package com.avances.applima.data.datasource.datastore;


import com.avances.applima.data.datasource.db.model.DbRoute;
import com.avances.applima.domain.repository.RepositoryCallback;

import java.util.List;

public interface RouteDataStore {

    void getRoutes(RepositoryCallback repositoryCallback);

    void createRoutes(List<DbRoute> dbRoutes, RepositoryCallback repositoryCallback);

    void updateRoute(DbRoute dbRoute, RepositoryCallback repositoryCallback);

}
