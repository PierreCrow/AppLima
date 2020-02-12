package com.avances.applima.interactor.interest;

import com.avances.applima.data.datasource.db.model.DbInterest;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.domain.model.Interest;
import com.avances.applima.domain.repository.InterestRepository;
import com.avances.applima.domain.repository.PlaceRepository;
import com.avances.applima.interactor.place.PlaceCreatedCallback;
import com.avances.applima.interactor.place.PlaceListCallback;
import com.avances.applima.interactor.place.PlaceUpdatedCallback;

import java.util.List;

public class InterestInteractor {

    private final InterestRepository interestRepository;

    public InterestInteractor(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public void getInterests(int store, InterestListCallback interestListCallback) {
        interestRepository.getInterests(store, interestListCallback);
    }

    public void createInterests(List<DbInterest> dbInterests, int store, InterestCreatedCallback interestCreatedCallback) {
        interestRepository.createInterests(dbInterests,store, interestCreatedCallback);
    }



}
