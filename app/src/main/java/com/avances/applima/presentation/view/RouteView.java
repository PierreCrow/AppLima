package com.avances.applima.presentation.view;

import com.avances.applima.domain.model.Place;
import com.avances.applima.domain.model.Route;

import java.util.List;

public interface RouteView extends BaseView {

    void routeListLoaded(List<Route> routes);

    void routeCreated(String message);

    void routeUpdated(String message);

    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);
}
