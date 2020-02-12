package com.avances.applima.interactor.distritneighborhood;

import com.avances.applima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.applima.domain.model.DistritNeighborhood;

import java.util.ArrayList;
import java.util.List;

public interface DistritNeighborhoodListCallback {

    void onGetDistritNeighborhoodListSuccess(List<DistritNeighborhood> dbDistritNeighborhoods);

    void onGetDistritNeighborhoodListError(String message);
}
