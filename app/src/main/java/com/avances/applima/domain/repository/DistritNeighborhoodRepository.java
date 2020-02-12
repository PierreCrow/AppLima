package com.avances.applima.domain.repository;

import com.avances.applima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.applima.interactor.distritneighborhood.DistritNeighborhoodCreatedCallback;
import com.avances.applima.interactor.distritneighborhood.DistritNeighborhoodListCallback;
import com.avances.applima.interactor.distritneighborhood.DistritNeighborhoodUpdatedCallback;

import java.util.List;

public interface DistritNeighborhoodRepository {

    void getDistritNeighborhoods(int store, DistritNeighborhoodListCallback distritNeighborhoodListCallback);

    void createDistritNeighborhood(List<DbDistritNeighborhood> dbDistritNeighborhoods, int store, DistritNeighborhoodCreatedCallback distritNeighborhoodCreatedCallback);

    void updateDistritNeighborhood(DbDistritNeighborhood dbDistritNeighborhood, int store, DistritNeighborhoodUpdatedCallback distritNeighborhoodUpdatedCallback);

}
