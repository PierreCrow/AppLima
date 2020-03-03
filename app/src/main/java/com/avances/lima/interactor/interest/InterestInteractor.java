package com.avances.lima.interactor.interest;

import com.avances.lima.data.datasource.db.model.DbInterest;
import com.avances.lima.domain.repository.InterestRepository;

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
