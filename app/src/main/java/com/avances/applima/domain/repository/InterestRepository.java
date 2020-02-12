package com.avances.applima.domain.repository;

import com.avances.applima.data.datasource.db.model.DbInterest;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.interactor.interest.InterestCreatedCallback;
import com.avances.applima.interactor.interest.InterestListCallback;
import com.avances.applima.interactor.place.PlaceCreatedCallback;
import com.avances.applima.interactor.place.PlaceListCallback;
import com.avances.applima.interactor.place.PlaceUpdatedCallback;

import java.util.List;

public interface InterestRepository {

    void getInterests(int store, InterestListCallback interestListCallback);

    void createInterests(List<DbInterest> dbInterests, int store, InterestCreatedCallback interestCreatedCallback);

    void updateInterest(DbInterest dbInterest, int store, PlaceUpdatedCallback placeUpdatedCallback);
}
