package com.avances.applima.data.datasource.db.store;

import android.content.Context;

import com.avances.applima.data.datasource.datastore.SuggestedTagDataStore;
import com.avances.applima.data.datasource.db.AppLimaDb;
import com.avances.applima.data.datasource.db.dao.SuggestedTagDao;
import com.avances.applima.data.datasource.db.model.DbSuggestedTag;
import com.avances.applima.data.mapper.SuggestedTagDataMapper;
import com.avances.applima.domain.model.Country;
import com.avances.applima.domain.model.SuggestedTag;
import com.avances.applima.domain.repository.RepositoryCallback;

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
