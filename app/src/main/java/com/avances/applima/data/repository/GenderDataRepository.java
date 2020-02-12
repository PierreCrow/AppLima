package com.avances.applima.data.repository;

import com.avances.applima.data.datasource.datastore.CountryDataStore;
import com.avances.applima.data.datasource.datastore.CountryDataStoreFactory;
import com.avances.applima.data.datasource.datastore.GenderDataStore;
import com.avances.applima.data.datasource.datastore.GenderDataStoreFactory;
import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.data.datasource.db.model.DbGender;
import com.avances.applima.domain.model.Country;
import com.avances.applima.domain.model.Gender;
import com.avances.applima.domain.repository.CountryRepository;
import com.avances.applima.domain.repository.GenderRepository;
import com.avances.applima.domain.repository.RepositoryCallback;
import com.avances.applima.interactor.country.CountryCreatedCallback;
import com.avances.applima.interactor.country.CountryListCallback;
import com.avances.applima.interactor.gender.GenderCreatedCallback;
import com.avances.applima.interactor.gender.GenderListCallback;

import java.util.List;

public class GenderDataRepository implements GenderRepository {

    private final GenderDataStoreFactory genderDataStoreFactory;

    public GenderDataRepository(GenderDataStoreFactory genderDataStoreFactory) {
        this.genderDataStoreFactory = genderDataStoreFactory;
    }



    @Override
    public void getGenders(int store, GenderListCallback genderListCallback) {
        final GenderDataStore genderDataStore = genderDataStoreFactory.create(store);
        genderDataStore.getGenders(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                genderListCallback.onGetGenderListError(message);
            }

            @Override
            public void onSuccess(Object object) {

                List<Gender> countries = (List<Gender>) object;
                genderListCallback.onGetGenderListSuccess(countries);
            }
        });
    }

    @Override
    public void createGender(List<DbGender> dbGenders, int store, GenderCreatedCallback genderCreatedCallback) {
        final GenderDataStore genderDataStore = genderDataStoreFactory.create(store);
        genderDataStore.createGender(dbGenders, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                genderCreatedCallback.onGenderCreatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                String message = (String) object;
                genderCreatedCallback.onGenderCreatedSuccess(message);
            }
        });
    }
}
