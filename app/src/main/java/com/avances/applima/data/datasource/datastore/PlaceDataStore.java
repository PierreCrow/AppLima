package com.avances.applima.data.datasource.datastore;


import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.domain.model.Place;
import com.avances.applima.domain.repository.RepositoryCallback;

import java.util.List;

public interface PlaceDataStore {

    void getPlaces(RepositoryCallback repositoryCallback);

    void createPlaces(List<DbPlace> dbPlaces, RepositoryCallback repositoryCallback);

    void updatePlace(Place place,boolean favorite, RepositoryCallback repositoryCallback);

}
