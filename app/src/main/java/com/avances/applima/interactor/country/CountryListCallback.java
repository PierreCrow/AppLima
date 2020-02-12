package com.avances.applima.interactor.country;

import com.avances.applima.domain.model.Country;
import java.util.List;

import io.opencensus.stats.Aggregation;

public interface CountryListCallback {

    void onGetCountryListSuccess(List<Country> countries);
    void onGetCountryListError(String message);
}
