package com.avances.lima.interactor.suggestedtag;

import com.avances.lima.data.datasource.db.model.DbSuggestedTag;
import com.avances.lima.domain.repository.SuggestedTagRepository;

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
