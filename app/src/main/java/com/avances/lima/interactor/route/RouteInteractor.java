package com.avances.lima.interactor.route;

import com.avances.lima.data.datasource.db.model.DbRoute;
import com.avances.lima.domain.repository.RouteRepository;


import java.util.List;

public class RouteInteractor {

    private final RouteRepository routeRepository;

    public RouteInteractor(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public void getRoutes(int store, RouteListCallback routeListCallback) {
        routeRepository.getRoutes(store, routeListCallback);
    }

    public void createRoutes(List<DbRoute> dbRoutes, int store, RouteCreatedCallback routeCreatedCallback) {
        routeRepository.createRoutes(dbRoutes, store, routeCreatedCallback);
    }

    public void updateRoute(DbRoute dbRoute, int store, RouteUpdatedCallback routeUpdatedCallback) {
        routeRepository.updateRoute(dbRoute, store, routeUpdatedCallback);
    }

}
