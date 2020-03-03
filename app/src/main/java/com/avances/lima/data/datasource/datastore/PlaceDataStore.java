package com.avances.lima.data.datasource.datastore;


import com.avances.lima.data.datasource.db.model.DbPlace;
import com.avances.lima.domain.model.Place;
import com.avances.lima.domain.repository.RepositoryCallback;

import java.util.List;

public interface PlaceDataStore {

    void getPlaces(RepositoryCallback repositoryCallback);

    void createPlaces(List<DbPlace> dbPlaces, RepositoryCallback repositoryCallback);

    void updatePlace(Place place,boolean favorite, RepositoryCallback repositoryCallback);

}
