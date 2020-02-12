package com.avances.applima.interactor.suggestedtag;

import com.avances.applima.domain.model.Country;
import com.avances.applima.domain.model.SuggestedTag;

import java.util.List;

public interface SuggestedTagListCallback {

    void onGetSuggestedTagListSuccess(List<SuggestedTag> suggestedTags);
    void onGetSuggestedTagListError(String message);
}
