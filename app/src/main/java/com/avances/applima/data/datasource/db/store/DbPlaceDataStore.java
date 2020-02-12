package com.avances.applima.data.datasource.db.store;

import android.content.Context;

import com.avances.applima.data.datasource.datastore.PlaceDataStore;
import com.avances.applima.data.datasource.db.AppLimaDb;
import com.avances.applima.data.datasource.db.dao.PlaceDao;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.data.mapper.PlaceDataMapper;
import com.avances.applima.domain.model.Place;
import com.avances.applima.domain.repository.RepositoryCallback;

import java.util.List;

public class DbPlaceDataStore implements PlaceDataStore {

    PlaceDao placeDao;

    public DbPlaceDataStore(Context context) {
        placeDao = AppLimaDb.getDatabase(context).placeDao();
    }

    @Override
    public void getPlaces(RepositoryCallback repositoryCallback) {

        try {
            PlaceDataMapper placeDataMapper= new PlaceDataMapper();

            List<DbPlace> dbPlaces= placeDao.GetAll();
            List<Place> places=placeDataMapper.transformDbToEntity(dbPlaces);
            repositoryCallback.onSuccess(places);

        } catch (Exception ex) {
            repositoryCallback.onError("Error");
        }

    }

    @Override
    public void createPlaces(List<DbPlace> dbPlaces, RepositoryCallback repositoryCallback) {

        if (dbPlaces.size() > 0) {
            try {
                placeDao.InsertMultiple(dbPlaces);
                repositoryCallback.onSuccess("Success");

            } catch (Exception ex) {
                repositoryCallback.onError("Error");
            }
        }
    }

    @Override
    public void updatePlace(Place place,boolean favorite, RepositoryCallback repositoryCallback) {

        PlaceDataMapper placeDataMapper= new PlaceDataMapper();
        DbPlace dbPlace= placeDataMapper.transformEntityToDb(place);

        try {
            placeDao.setFavorite(favorite,dbPlace.getIdCloud());
            repositoryCallback.onSuccess("Success");

        } catch (Exception ex) {
            repositoryCallback.onError("Error");
        }

    }
}
