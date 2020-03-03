package com.avances.lima.presentation.view;

import com.avances.lima.domain.model.DistritNeighborhood;

import java.util.List;

public interface DistritNeighborhoodView extends BaseView {

    void distritNeighborhoodListLoaded(List<DistritNeighborhood> distritNeighborhoods);

    void distritCreated(String message);

    void distritUpdated(String message);

    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);
}
