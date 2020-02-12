package com.avances.applima.presentation.view;

import com.avances.applima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.applima.domain.model.DistritNeighborhood;

import java.util.ArrayList;
import java.util.List;

public interface DistritNeighborhoodView extends BaseView {

    void distritNeighborhoodListLoaded(List<DistritNeighborhood> distritNeighborhoods);

    void distritCreated(String message);

    void distritUpdated(String message);

    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);
}
