package com.avances.lima.data.datasource.db.store;

import android.content.Context;

import com.avances.lima.data.datasource.datastore.DistritNeighborhoodDataStore;
import com.avances.lima.data.datasource.db.AppLimaDb;
import com.avances.lima.data.datasource.db.dao.DistritNeighborhoodDao;
import com.avances.lima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.lima.data.mapper.DistritNeighborhoodDataMapper;
import com.avances.lima.domain.model.DistritNeighborhood;
import com.avances.lima.domain.repository.RepositoryCallback;

import java.util.List;

public class DbDistritNeighborhoodDataStore implements DistritNeighborhoodDataStore {

    DistritNeighborhoodDao distritNeighborhoodDao;

    public DbDistritNeighborhoodDataStore(Context context) {
        distritNeighborhoodDao= AppLimaDb.getDatabase(context).distritNeighborhoodDao();
    }


    @Override
    public void getDistritNeighborhoods(RepositoryCallback repositoryCallback) {

        try {
            DistritNeighborhoodDataMapper interestDataMapper = new DistritNeighborhoodDataMapper();

            List<DbDistritNeighborhood> dbInterests = distritNeighborhoodDao.GetAll();
            List<DistritNeighborhood> interests = interestDataMapper.transformDbToEntity(dbInterests);
            repositoryCallback.onSuccess(interests);

        } catch (Exception ex) {
            repositoryCallback.onError("Error");
        }
    }

    @Override
    public void createDistritNeighborhood(List<DbDistritNeighborhood> dbDistritNeighborhoods, RepositoryCallback repositoryCallback) {
        if (dbDistritNeighborhoods.size() > 0) {
            try {
                distritNeighborhoodDao.InsertMultiple(dbDistritNeighborhoods);
                repositoryCallback.onSuccess("Success");

            } catch (Exception ex) {
                repositoryCallback.onError("Error");
            }
        }
    }

    @Override
    public void updateDistritNeighborhood(DbDistritNeighborhood dbDistritNeighborhood, RepositoryCallback repositoryCallback) {

    }
}
