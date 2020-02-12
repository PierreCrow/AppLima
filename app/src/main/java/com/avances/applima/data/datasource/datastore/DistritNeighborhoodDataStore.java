package com.avances.applima.data.datasource.datastore;


import com.avances.applima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.applima.domain.repository.RepositoryCallback;

import java.util.List;

public interface DistritNeighborhoodDataStore {

    void getDistritNeighborhoods(RepositoryCallback repositoryCallback);

    void createDistritNeighborhood(List<DbDistritNeighborhood> dbDistritNeighborhoods, RepositoryCallback repositoryCallback);

    void updateDistritNeighborhood(DbDistritNeighborhood dbDistritNeighborhood, RepositoryCallback repositoryCallback);

}
