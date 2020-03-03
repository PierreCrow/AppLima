package com.avances.lima.data.datasource.datastore;


import com.avances.lima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.lima.domain.repository.RepositoryCallback;

import java.util.List;

public interface DistritNeighborhoodDataStore {

    void getDistritNeighborhoods(RepositoryCallback repositoryCallback);

    void createDistritNeighborhood(List<DbDistritNeighborhood> dbDistritNeighborhoods, RepositoryCallback repositoryCallback);

    void updateDistritNeighborhood(DbDistritNeighborhood dbDistritNeighborhood, RepositoryCallback repositoryCallback);

}
