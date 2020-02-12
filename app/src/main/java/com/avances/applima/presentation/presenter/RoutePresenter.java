package com.avances.applima.presentation.presenter;

import com.avances.applima.data.datasource.datastore.RouteDataStoreFactory;
import com.avances.applima.data.datasource.db.model.DbRoute;
import com.avances.applima.data.repository.RouteDataRepository;
import com.avances.applima.domain.model.Route;
import com.avances.applima.domain.repository.RouteRepository;
import com.avances.applima.interactor.route.RouteCreatedCallback;
import com.avances.applima.interactor.route.RouteInteractor;
import com.avances.applima.interactor.route.RouteListCallback;
import com.avances.applima.interactor.route.RouteUpdatedCallback;
import com.avances.applima.presentation.view.RouteView;

import java.util.List;

public class RoutePresenter implements Presenter<RouteView>, RouteListCallback, RouteCreatedCallback, RouteUpdatedCallback {

    private RouteView routeView;
    private RouteInteractor routeInteractor;


    public void getRoutes(int store) {
        routeInteractor.getRoutes(store, this);
    }

    public void createRoutes(List<DbRoute> dbRoutes, int store) {
        routeInteractor.createRoutes(dbRoutes, store, this);
    }

    public void updateRoute(DbRoute dbRoutes, int store) {
        routeInteractor.updateRoute(dbRoutes, store, this);
    }


    @Override
    public void onRouteCreatedSuccess(String message) {
        routeView.routeCreated(message);
    }

    @Override
    public void onRouteCreatedError(String message) {
        routeView.showErrorMessage(message);
    }

    @Override
    public void onGetRouteListSuccess(List<Route> routes) {
        routeView.routeListLoaded(routes);
    }

    @Override
    public void onGetRouteListError(String message) {
        routeView.showErrorMessage(message);
    }

    @Override
    public void onRouteUpdatedSuccess(String message) {
        routeView.routeUpdated(message);
    }

    @Override
    public void onRouteUpdatedError(String message) {
        routeView.showErrorMessage(message);
    }

    @Override
    public void addView(RouteView view) {
        this.routeView = view;
        RouteRepository routeRepository = new RouteDataRepository(new RouteDataStoreFactory(this.routeView.getContext()));
        routeInteractor = new RouteInteractor(routeRepository);
    }

    @Override
    public void removeView(RouteView view) {

    }
}
