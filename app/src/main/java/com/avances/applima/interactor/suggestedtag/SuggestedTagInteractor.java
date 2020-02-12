package com.avances.applima.interactor.suggestedtag;

import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.data.datasource.db.model.DbSuggestedTag;
import com.avances.applima.domain.repository.CountryRepository;
import com.avances.applima.domain.repository.SuggestedTagRepository;
import com.avances.applima.interactor.country.CountryCreatedCallback;
import com.avances.applima.interactor.country.CountryListCallback;

import java.util.List;

public class SuggestedTagInteractor {

    private final SuggestedTagRepository suggestedTagRepository;

    public SuggestedTagInteractor(SuggestedTagRepository suggestedTagRepository) {
        this.suggestedTagRepository = suggestedTagRepository;
    }

    public void getSuggestedTags(int store, SuggestedTagListCallback suggestedTagListCallback) {
        suggestedTagRepository.getSuggestedTags(store, suggestedTagListCallback);
    }

    public void createSuggestedTag(List<DbSuggestedTag> dbSuggestedTags, int store, SuggestedTagCreatedCallback suggestedTagCreatedCallback) {
        suggestedTagRepository.createSuggestedTag(dbSuggestedTags,store, suggestedTagCreatedCallback);
    }

}
