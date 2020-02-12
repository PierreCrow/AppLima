package com.avances.applima.presentation.view;

import com.avances.applima.domain.model.Gender;

import java.util.List;

public interface GenderView extends BaseView {

    void genderListLoaded(List<Gender> genders);

    void genderCreated(String message);


    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);
}
