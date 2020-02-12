package com.avances.applima.interactor.suggestedtag;

public interface SuggestedTagCreatedCallback {

    void onSuggestedTagCreatedSuccess(String message);

    void onSuggestedTagCreatedError(String message);
}
