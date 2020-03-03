package com.avances.lima.presentation.view;

import com.avances.lima.domain.model.Gender;

import java.util.List;

public interface GenderView extends BaseView {

    void genderListLoaded(List<Gender> genders);

    void genderCreated(String message);


    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);
}
