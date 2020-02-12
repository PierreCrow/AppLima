package com.avances.applima.data.repository;

import com.avances.applima.data.datasource.datastore.CountryDataStore;
import com.avances.applima.data.datasource.datastore.CountryDataStoreFactory;
import com.avances.applima.data.datasource.datastore.SuggestedTagDataStore;
import com.avances.applima.data.datasource.datastore.SuggestedTagDataStoreFactory;
import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.data.datasource.db.model.DbSuggestedTag;
import com.avances.applima.domain.model.Country;
import com.avances.applima.domain.model.SuggestedTag;
import com.avances.applima.domain.repository.CountryRepository;
import com.avances.applima.domain.repository.RepositoryCallback;
import com.avances.applima.domain.repository.SuggestedTagRepository;
import com.avances.applima.interactor.country.CountryCreatedCallback;
import com.avances.applima.interactor.country.CountryListCallback;
import com.avances.applima.interactor.suggestedtag.SuggestedTagCreatedCallback;
import com.avances.applima.interactor.suggestedtag.SuggestedTagListCallback;

import java.util.List;

public class SuggestedTagDataRepository implements SuggestedTagRepository {

    private final SuggestedTagDataStoreFactory suggestedTagDataStoreFactory;

    public SuggestedTagDataRepository(SuggestedTagDataStoreFactory suggestedTagDataStoreFactory) {
        this.suggestedTagDataStoreFactory = suggestedTagDataStoreFactory;
    }




    @Override
    public void getSuggestedTags(int store, SuggestedTagListCallback suggestedTagListCallback) {
        final SuggestedTagDataStore suggestedTagDataStore = suggestedTagDataStoreFactory.create(store);
        suggestedTagDataStore.getSuggestedTags(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                suggestedTagListCallback.onGetSuggestedTagListError(message);
            }

            @Override
            public void onSuccess(Object object) {

                List<SuggestedTag> suggestedTags = (List<SuggestedTag>) object;
                suggestedTagListCallback.onGetSuggestedTagListSuccess(suggestedTags);
            }
        });
    }

    @Override
    public void createSuggestedTag(List<DbSuggestedTag> dbSuggestedTags, int store, SuggestedTagCreatedCallback suggestedTagCreatedCallback) {
        final SuggestedTagDataStore suggestedTagDataStore = suggestedTagDataStoreFactory.create(store);
        suggestedTagDataStore.createSuggestedTag(dbSuggestedTags, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                suggestedTagCreatedCallback.onSuggestedTagCreatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                String message = (String) object;
                suggestedTagCreatedCallback.onSuggestedTagCreatedSuccess(message);
            }
        });
    }
}
