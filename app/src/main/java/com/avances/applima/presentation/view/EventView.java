package com.avances.applima.presentation.view;

import com.avances.applima.domain.model.Event;

import java.util.List;

public interface EventView extends BaseView {

    void eventListLoaded(List<Event> events);

    void eventCreated(String message);

    void eventUpdated(String message);

    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);
}
