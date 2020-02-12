package com.avances.applima.presentation.presenter;

import com.avances.applima.data.datasource.datastore.CountryDataStoreFactory;
import com.avances.applima.data.datasource.datastore.SuggestedTagDataStoreFactory;
import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.data.datasource.db.model.DbSuggestedTag;
import com.avances.applima.data.repository.CountryDataRepository;
import com.avances.applima.data.repository.SuggestedTagDataRepository;
import com.avances.applima.domain.model.Country;
import com.avances.applima.domain.model.SuggestedTag;
import com.avances.applima.domain.repository.CountryRepository;
import com.avances.applima.domain.repository.SuggestedTagRepository;
import com.avances.applima.interactor.country.CountryInteractor;
import com.avances.applima.interactor.suggestedtag.SuggestedTagCreatedCallback;
import com.avances.applima.interactor.suggestedtag.SuggestedTagInteractor;
import com.avances.applima.interactor.suggestedtag.SuggestedTagListCallback;
import com.avances.applima.presentation.view.CountryView;
import com.avances.applima.presentation.view.SuggestedTagView;

import java.util.List;

public class SuggestedTagPresenter implements Presenter<SuggestedTagView>, SuggestedTagListCallback,
        SuggestedTagCreatedCallback {

    private SuggestedTagView suggestedTagView;
    private SuggestedTagInteractor suggestedTagInteractor;


    public void getSuggestedTags(int store) {
        suggestedTagInteractor.getSuggestedTags(store, this);
    }

    public void createSuggestedTag(List<DbSuggestedTag> dbSuggestedTags, int store) {
        suggestedTagInteractor.createSuggestedTag(dbSuggestedTags, store, this);
    }


    @Override
    public void onSuggestedTagCreatedSuccess(String message) {
        suggestedTagView.suggestedTagCreated(message);
    }

    @Override
    public void onSuggestedTagCreatedError(String message) {
        suggestedTagView.showErrorMessage(message);
    }

    @Override
    public void onGetSuggestedTagListSuccess(List<SuggestedTag> suggestedTags) {
        suggestedTagView.suggestedTagListLoaded(suggestedTags);
    }

    @Override
    public void onGetSuggestedTagListError(String message) {
        suggestedTagView.showErrorMessage(message);
    }

    @Override
    public void addView(SuggestedTagView view) {
        this.suggestedTagView = view;
        SuggestedTagRepository suggestedTagRepository = new SuggestedTagDataRepository(new SuggestedTagDataStoreFactory(this.suggestedTagView.getContext()));
        suggestedTagInteractor = new SuggestedTagInteractor(suggestedTagRepository);
    }

    @Override
    public void removeView(SuggestedTagView view) {

    }
}
