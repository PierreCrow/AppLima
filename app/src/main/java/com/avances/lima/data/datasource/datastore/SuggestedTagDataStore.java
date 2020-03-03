package com.avances.lima.data.datasource.datastore;


import com.avances.lima.data.datasource.db.model.DbSuggestedTag;
import com.avances.lima.domain.repository.RepositoryCallback;

import java.util.List;

public interface SuggestedTagDataStore {

    void getSuggestedTags(RepositoryCallback repositoryCallback);

    void createSuggestedTag(List<DbSuggestedTag> dbSuggestedTags, RepositoryCallback repositoryCallback);

}
