package com.avances.applima.domain.repository;

import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.data.datasource.db.model.DbSuggestedTag;
import com.avances.applima.interactor.country.CountryCreatedCallback;
import com.avances.applima.interactor.country.CountryListCallback;
import com.avances.applima.interactor.suggestedtag.SuggestedTagCreatedCallback;
import com.avances.applima.interactor.suggestedtag.SuggestedTagListCallback;

import java.util.List;

public interface SuggestedTagRepository {

    void getSuggestedTags(int store, SuggestedTagListCallback suggestedTagListCallback);

    void createSuggestedTag(List<DbSuggestedTag> dbSuggestedTags, int store, SuggestedTagCreatedCallback suggestedTagCreatedCallback);

}
