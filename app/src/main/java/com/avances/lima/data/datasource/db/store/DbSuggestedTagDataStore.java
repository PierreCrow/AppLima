package com.avances.lima.data.datasource.db.store;

import android.content.Context;

import com.avances.lima.data.datasource.datastore.SuggestedTagDataStore;
import com.avances.lima.data.datasource.db.AppLimaDb;
import com.avances.lima.data.datasource.db.dao.SuggestedTagDao;
import com.avances.lima.data.datasource.db.model.DbSuggestedTag;
import com.avances.lima.data.mapper.SuggestedTagDataMapper;
import com.avances.lima.domain.model.SuggestedTag;
import com.avances.lima.domain.repository.RepositoryCallback;

import java.util.List;

public class DbSuggestedTagDataStore implements SuggestedTagDataStore {

    SuggestedTagDao suggestedTagDao;

    public DbSuggestedTagDataStore(Context context) {
        suggestedTagDao = AppLimaDb.getDatabase(context).suggestedTagDao();
    }





    @Override
    public void getSuggestedTags(RepositoryCallback repositoryCallback) {
        try {
            SuggestedTagDataMapper countryDataMapper = new SuggestedTagDataMapper();

            List<DbSuggestedTag> dbSuggestedTags = suggestedTagDao.GetAll();
            List<SuggestedTag> suggestedTags = countryDataMapper.transformDbToEntity(dbSuggestedTags);
            repositoryCallback.onSuccess(suggestedTags);

        } catch (Exception ex) {
            repositoryCallback.onError("Error");
        }
    }

    @Override
    public void createSuggestedTag(List<DbSuggestedTag> dbSuggestedTags, RepositoryCallback repositoryCallback) {
        if (dbSuggestedTags.size() > 0) {
            try {
                suggestedTagDao.InsertMultiple(dbSuggestedTags);
                repositoryCallback.onSuccess("Success");

            } catch (Exception ex) {
                repositoryCallback.onError("Error");
            }
        }
    }
}
