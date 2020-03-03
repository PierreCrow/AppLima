package com.avances.lima.domain.repository;

import com.avances.lima.data.datasource.db.model.DbInterest;
import com.avances.lima.interactor.interest.InterestCreatedCallback;
import com.avances.lima.interactor.interest.InterestListCallback;
import com.avances.lima.interactor.place.PlaceUpdatedCallback;

import java.util.List;

public interface InterestRepository {

    void getInterests(int store, InterestListCallback interestListCallback);

    void createInterests(List<DbInterest> dbInterests, int store, InterestCreatedCallback interestCreatedCallback);

    void updateInterest(DbInterest dbInterest, int store, PlaceUpdatedCallback placeUpdatedCallback);
}
