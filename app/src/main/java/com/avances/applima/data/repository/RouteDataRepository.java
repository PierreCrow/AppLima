package com.avances.applima.data.repository;

import com.avances.applima.data.datasource.datastore.PlaceDataStore;
import com.avances.applima.data.datasource.datastore.PlaceDataStoreFactory;
import com.avances.applima.data.datasource.datastore.RouteDataStore;
import com.avances.applima.data.datasource.datastore.RouteDataStoreFactory;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.data.datasource.db.model.DbRoute;
import com.avances.applima.domain.model.Place;
import com.avances.applima.domain.model.Route;
import com.avances.applima.domain.repository.RepositoryCallback;
import com.avances.applima.domain.repository.RouteRepository;
import com.avances.applima.interactor.place.PlaceCreatedCallback;
import com.avances.applima.interactor.place.PlaceListCallback;
import com.avances.applima.interactor.place.PlaceUpdatedCallback;
import com.avances.applima.interactor.route.RouteCreatedCallback;
import com.avances.applima.interactor.route.RouteListCallback;
import com.avances.applima.interactor.route.RouteUpdatedCallback;

import java.util.List;

public class RouteDataRepository implements RouteRepository {

    private final RouteDataStoreFactory routeDataStoreFactory;

    public RouteDataRepository(RouteDataStoreFactory routeDataStoreFactory) {
        this.routeDataStoreFactory = routeDataStoreFactory;
    }


    @Override
    public void getRoutes(int store, RouteListCallback routeListCallback) {
        final RouteDataStore routeDataStore = routeDataStoreFactory.create(store);
        routeDataStore.getRoutes(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                routeListCallback.onGetRouteListError(message);
            }

            @Override
            public void onSuccess(Object object) {

                List<Route> routes = (List<Route>) object;
                routeListCallback.onGetRouteListSuccess(routes);
            }
        });
    }

    @Override
    public void createRoutes(List<DbRoute> dbRoutes, int store, RouteCreatedCallback routeCreatedCallback) {
        final RouteDataStore routeDataStore = routeDataStoreFactory.create(store);
        routeDataStore.createRoutes(dbRoutes, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                routeCreatedCallback.onRouteCreatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                String message = (String) object;
                routeCreatedCallback.onRouteCreatedSuccess(message);
            }
        });
    }

    @Override
    public void updateRoute(DbRoute dbRoute, int store, RouteUpdatedCallback routeUpdatedCallback) {
        final RouteDataStore routeDataStore = routeDataStoreFactory.create(store);
        routeDataStore.updateRoute(dbRoute, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                routeUpdatedCallback.onRouteUpdatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                String message = (String) object;
                routeUpdatedCallback.onRouteUpdatedSuccess(message);
            }
        });
    }
}
