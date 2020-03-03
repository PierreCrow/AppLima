package com.avances.lima.domain.repository;

import com.avances.lima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.lima.interactor.distritneighborhood.DistritNeighborhoodCreatedCallback;
import com.avances.lima.interactor.distritneighborhood.DistritNeighborhoodListCallback;
import com.avances.lima.interactor.distritneighborhood.DistritNeighborhoodUpdatedCallback;

import java.util.List;

public interface DistritNeighborhoodRepository {

    void getDistritNeighborhoods(int store, DistritNeighborhoodListCallback distritNeighborhoodListCallback);

    void createDistritNeighborhood(List<DbDistritNeighborhood> dbDistritNeighborhoods, int store, DistritNeighborhoodCreatedCallback distritNeighborhoodCreatedCallback);

    void updateDistritNeighborhood(DbDistritNeighborhood dbDistritNeighborhood, int store, DistritNeighborhoodUpdatedCallback distritNeighborhoodUpdatedCallback);

}
