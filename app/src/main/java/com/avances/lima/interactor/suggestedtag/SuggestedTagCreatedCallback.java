package com.avances.lima.interactor.suggestedtag;

public interface SuggestedTagCreatedCallback {

    void onSuggestedTagCreatedSuccess(String message);

    void onSuggestedTagCreatedError(String message);
}
