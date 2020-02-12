package com.avances.applima.data.datasource.datastore;


import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.data.datasource.db.model.DbSuggestedTag;
import com.avances.applima.domain.repository.RepositoryCallback;

import java.util.List;

public interface SuggestedTagDataStore {

    void getSuggestedTags(RepositoryCallback repositoryCallback);

    void createSuggestedTag(List<DbSuggestedTag> dbSuggestedTags, RepositoryCallback repositoryCallback);

}
