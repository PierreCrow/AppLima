package com.avances.lima.data.repository;

import com.avances.lima.data.datasource.datastore.InterestDataStore;
import com.avances.lima.data.datasource.datastore.InterestDataStoreFactory;
import com.avances.lima.data.datasource.db.model.DbInterest;
import com.avances.lima.domain.model.Interest;
import com.avances.lima.domain.repository.InterestRepository;
import com.avances.lima.domain.repository.RepositoryCallback;
import com.avances.lima.interactor.interest.InterestCreatedCallback;
import com.avances.lima.interactor.interest.InterestListCallback;
import com.avances.lima.interactor.place.PlaceUpdatedCallback;

import java.util.List;

public class InterestDataRepository implements InterestRepository {

    private final InterestDataStoreFactory interestDataStoreFactory;

    public InterestDataRepository(InterestDataStoreFactory interestDataStoreFactory) {
        this.interestDataStoreFactory = interestDataStoreFactory;
    }


    @Override
    public void getInterests(int store, InterestListCallback interestListCallback) {
        final InterestDataStore interestDataStore = interestDataStoreFactory.create(store);
        interestDataStore.getInterests(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                interestListCallback.onGetInterestListError(message);
            }

            @Override
            public void onSuccess(Object object) {

                List<Interest> interests = (List<Interest>) object;
                interestListCallback.onGetInterestListSuccess(interests);
            }
        });
    }

    @Override
    public void createInterests(List<DbInterest> dbInterests, int store, InterestCreatedCallback interestCreatedCallback) {
        final InterestDataStore interestDataStore = interestDataStoreFactory.create(store);
        interestDataStore.createInterests(dbInterests, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                interestCreatedCallback.onInterestCreatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                String message = (String) object;
                interestCreatedCallback.onInterestCreatedSuccess(message);
            }
        });
    }

    @Override
    public void updateInterest(DbInterest dbInterest, int store, PlaceUpdatedCallback placeUpdatedCallback) {

    }
}
