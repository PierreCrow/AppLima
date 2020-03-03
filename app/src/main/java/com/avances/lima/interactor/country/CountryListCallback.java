package com.avances.lima.interactor.country;

import com.avances.lima.domain.model.Country;
import java.util.List;

public interface CountryListCallback {

    void onGetCountryListSuccess(List<Country> countries);
    void onGetCountryListError(String message);
}
