package com.avances.applima.data.datasource.cloud.store;

import com.avances.applima.data.datasource.cloud.apiclient.ApiClient;
import com.avances.applima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.applima.data.datasource.cloud.model.synchronization.WsSynchronization;
import com.avances.applima.data.datasource.datastore.SynchronizationDataStore;
import com.avances.applima.domain.repository.RepositoryCallback;
import com.avances.applima.presentation.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CloudSynchronizationDataStore implements SynchronizationDataStore {
    private static final String TAG = "CloudUsuarioDataStore";

    private ApiInterface apiInterface;

    public CloudSynchronizationDataStore() {
        //  apiInterface = ApiClient.getApiClient();
    }

    @Override
    public void syncAll(String token, RepositoryCallback repositoryCallback) {

        Call<WsSynchronization> call = ApiClient.getApiClient(token).getTotalSynchronization();
        call.enqueue(new Callback<WsSynchronization>() {
            @Override
            public void onResponse(Call<WsSynchronization> call, Response<WsSynchronization> response) {

                if (response.code() == 200) {
                    if (response.body() != null) {
                        WsSynchronization wsSynchronization = response.body();
                        if (wsSynchronization.getWsResponse().getCode().equals(Constants.RESPONSE_CODES.SUCCESS)) {
                            repositoryCallback.onSuccess(wsSynchronization);
                        } else {
                            repositoryCallback.onError(wsSynchronization.getWsResponse().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsSynchronization> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }
}
