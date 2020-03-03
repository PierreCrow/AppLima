package com.avances.lima.data.repository;

import com.avances.lima.data.datasource.datastore.CountryDataStore;
import com.avances.lima.data.datasource.datastore.CountryDataStoreFactory;
import com.avances.lima.data.datasource.db.model.DbCountry;
import com.avances.lima.domain.model.Country;
import com.avances.lima.domain.repository.CountryRepository;
import com.avances.lima.domain.repository.RepositoryCallback;
import com.avances.lima.interactor.country.CountryCreatedCallback;
import com.avances.lima.interactor.country.CountryListCallback;

import java.util.List;

public class CountryDataRepository implements CountryRepository {

    private final CountryDataStoreFactory countryDataStoreFactory;

    public CountryDataRepository(CountryDataStoreFactory countryDataStoreFactory) {
        this.countryDataStoreFactory = countryDataStoreFactory;
    }


    @Override
    public void getCountries(int store, CountryListCallback countryListCallback) {
        final CountryDataStore countryDataStore = countryDataStoreFactory.create(store);
        countryDataStore.getCountries(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                countryListCallback.onGetCountryListError(message);
            }

            @Override
            public void onSuccess(Object object) {

                List<Country> countries = (List<Country>) object;
                countryListCallback.onGetCountryListSuccess(countries);
            }
        });
    }

    @Override
    public void createCountry(List<DbCountry> dbCountries, int store, CountryCreatedCallback countryCreatedCallback) {
        final CountryDataStore countryDataStore = countryDataStoreFactory.create(store);
        countryDataStore.createCountry(dbCountries, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                countryCreatedCallback.onCountryCreatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                String message = (String) object;
                countryCreatedCallback.onCountryCreatedSuccess(message);
            }
        });
    }
}
