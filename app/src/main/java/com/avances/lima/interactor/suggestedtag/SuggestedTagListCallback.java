package com.avances.lima.interactor.suggestedtag;

import com.avances.lima.domain.model.SuggestedTag;

import java.util.List;

public interface SuggestedTagListCallback {

    void onGetSuggestedTagListSuccess(List<SuggestedTag> suggestedTags);
    void onGetSuggestedTagListError(String message);
}
