package com.avances.lima.interactor.distritneighborhood;

import com.avances.lima.domain.model.DistritNeighborhood;

import java.util.List;

public interface DistritNeighborhoodListCallback {

    void onGetDistritNeighborhoodListSuccess(List<DistritNeighborhood> dbDistritNeighborhoods);

    void onGetDistritNeighborhoodListError(String message);
}
