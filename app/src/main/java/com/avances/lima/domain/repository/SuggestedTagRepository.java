package com.avances.lima.domain.repository;

import com.avances.lima.data.datasource.db.model.DbSuggestedTag;
import com.avances.lima.interactor.suggestedtag.SuggestedTagCreatedCallback;
import com.avances.lima.interactor.suggestedtag.SuggestedTagListCallback;

import java.util.List;

public interface SuggestedTagRepository {

    void getSuggestedTags(int store, SuggestedTagListCallback suggestedTagListCallback);

    void createSuggestedTag(List<DbSuggestedTag> dbSuggestedTags, int store, SuggestedTagCreatedCallback suggestedTagCreatedCallback);

}
