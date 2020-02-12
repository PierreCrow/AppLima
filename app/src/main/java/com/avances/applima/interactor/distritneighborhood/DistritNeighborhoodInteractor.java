package com.avances.applima.interactor.distritneighborhood;

import com.avances.applima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.applima.domain.repository.DistritNeighborhoodRepository;

import java.util.List;

public class DistritNeighborhoodInteractor {

    private final DistritNeighborhoodRepository distritNeighborhoodRepository;

    public DistritNeighborhoodInteractor(DistritNeighborhoodRepository distritNeighborhoodRepository) {
        this.distritNeighborhoodRepository = distritNeighborhoodRepository;
    }

    public void getDistritNeighborhoods(int store, DistritNeighborhoodListCallback placeListCallback) {
        distritNeighborhoodRepository.getDistritNeighborhoods(store, placeListCallback);
    }

    public void createDistritNeighborhoods(List<DbDistritNeighborhood> dbDistritNeighborhoods, int store, DistritNeighborhoodCreatedCallback distritNeighborhoodCreatedCallback) {
        distritNeighborhoodRepository.createDistritNeighborhood(dbDistritNeighborhoods,store, distritNeighborhoodCreatedCallback);
    }

    public void updateDistritNeighborhood(DbDistritNeighborhood dbDistritNeighborhood, int store, DistritNeighborhoodUpdatedCallback distritNeighborhoodUpdatedCallback) {
        distritNeighborhoodRepository.updateDistritNeighborhood(dbDistritNeighborhood,store, distritNeighborhoodUpdatedCallback);
    }

}
