package com.avances.applima.domain.repository;

import com.avances.applima.data.datasource.db.model.DbRoute;
import com.avances.applima.interactor.route.RouteCreatedCallback;
import com.avances.applima.interactor.route.RouteListCallback;
import com.avances.applima.interactor.route.RouteUpdatedCallback;

import java.util.List;

public interface RouteRepository {

    void getRoutes(int store, RouteListCallback routeListCallback);

    void createRoutes(List<DbRoute> dbRoutes, int store, RouteCreatedCallback routeCreatedCallback);

    void updateRoute(DbRoute dbRoute, int store, RouteUpdatedCallback routeUpdatedCallback);
}
