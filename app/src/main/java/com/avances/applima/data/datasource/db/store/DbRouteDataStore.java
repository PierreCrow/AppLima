package com.avances.applima.data.datasource.db.store;

import android.content.Context;

import com.avances.applima.data.datasource.datastore.RouteDataStore;
import com.avances.applima.data.datasource.db.AppLimaDb;
import com.avances.applima.data.datasource.db.dao.RouteDao;
import com.avances.applima.data.datasource.db.model.DbRoute;
import com.avances.applima.data.mapper.RouteDataMapper;
import com.avances.applima.domain.model.Route;
import com.avances.applima.domain.repository.RepositoryCallback;

import java.util.List;

public class DbRouteDataStore implements RouteDataStore {

    RouteDao routeDao;

    public DbRouteDataStore(Context context) {
        routeDao = AppLimaDb.getDatabase(context).routeDao();
    }


    @Override
    public void getRoutes(RepositoryCallback repositoryCallback) {
        try {
            RouteDataMapper routeDataMapper= new RouteDataMapper();

            List<DbRoute> dbRoutes= routeDao.GetAll();
            List<Route> routes=routeDataMapper.transformDbToEntity(dbRoutes);
            repositoryCallback.onSuccess(routes);

        } catch (Exception ex) {
            repositoryCallback.onError("Error");
        }
    }

    @Override
    public void createRoutes(List<DbRoute> dbRoutes, RepositoryCallback repositoryCallback) {
        if (dbRoutes.size() > 0) {
            try {
                routeDao.InsertMultiple(dbRoutes);
                repositoryCallback.onSuccess("Success");

            } catch (Exception ex) {
                repositoryCallback.onError("Error");
            }
        }
    }

    @Override
    public void updateRoute(DbRoute dbRoute, RepositoryCallback repositoryCallback) {

    }
}
