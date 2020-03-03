package com.avances.lima.domain.repository;

import com.avances.lima.data.datasource.db.model.DbRoute;
import com.avances.lima.interactor.route.RouteCreatedCallback;
import com.avances.lima.interactor.route.RouteListCallback;
import com.avances.lima.interactor.route.RouteUpdatedCallback;

import java.util.List;

public interface RouteRepository {

    void getRoutes(int store, RouteListCallback routeListCallback);

    void createRoutes(List<DbRoute> dbRoutes, int store, RouteCreatedCallback routeCreatedCallback);

    void updateRoute(DbRoute dbRoute, int store, RouteUpdatedCallback routeUpdatedCallback);
}
