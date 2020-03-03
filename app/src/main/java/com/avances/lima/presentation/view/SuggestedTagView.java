package com.avances.lima.presentation.view;

import com.avances.lima.domain.model.SuggestedTag;

import java.util.List;

public interface SuggestedTagView extends BaseView {

    void suggestedTagListLoaded(List<SuggestedTag> suggestedTags);

    void suggestedTagCreated(String message);


    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);
}
