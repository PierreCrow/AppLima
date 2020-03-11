package com.avances.lima.data.repository;

import com.avances.lima.data.datasource.datastore.PermanencyDayDataStore;
import com.avances.lima.data.datasource.datastore.PermanencyDayDataStoreFactory;
import com.avances.lima.data.datasource.db.model.DbPermanencyDay;
import com.avances.lima.domain.model.PermanencyDay;
import com.avances.lima.domain.repository.PermanencyDayRepository;
import com.avances.lima.domain.repository.RepositoryCallback;
import com.avances.lima.interactor.permanencyday.PermanencyDayCreatedCallback;
import com.avances.lima.interactor.permanencyday.PermanencyDayListCallback;

import java.util.List;

public class PermanencyDayDataRepository implements PermanencyDayRepository {

    private final PermanencyDayDataStoreFactory permanencyDayDataStoreFactory;

    public PermanencyDayDataRepository(PermanencyDayDataStoreFactory permanencyDayDataStoreFactory) {
        this.permanencyDayDataStoreFactory = permanencyDayDataStoreFactory;
    }

    @Override
    public void getPermanencyDays(int store, PermanencyDayListCallback permanencyDayListCallback) {
        final PermanencyDayDataStore permanencyDayDataStore = permanencyDayDataStoreFactory.create(store);
        permanencyDayDataStore.getPermanencyDays(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                permanencyDayListCallback.onGetPermanencyDayListError(message);
            }

            @Override
            public void onSuccess(Object object) {

                List<PermanencyDay> permanencyDays = (List<PermanencyDay>) object;
                permanencyDayListCallback.onGetPermanencyDayListSuccess(permanencyDays);
            }
        });
    }

    @Override
    public void createPermanencyDay(List<DbPermanencyDay> dbPermanencyDays, int store, PermanencyDayCreatedCallback permanencyDayCreatedCallback) {

        final PermanencyDayDataStore permanencyDayDataStore = permanencyDayDataStoreFactory.create(store);
        permanencyDayDataStore.createPermanencyDay(dbPermanencyDays, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                permanencyDayCreatedCallback.onPermanencyDayCreatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                String message = (String) object;
                permanencyDayCreatedCallback.onPermanencyDayCreatedSuccess(message);
            }
        });
    }
}
