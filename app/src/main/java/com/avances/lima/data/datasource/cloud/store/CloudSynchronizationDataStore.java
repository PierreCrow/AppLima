package com.avances.lima.data.datasource.cloud.store;

import com.avances.lima.data.datasource.cloud.apiclient.ApiClient;
import com.avances.lima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.lima.data.datasource.cloud.model.security.parameter.WsParameterVerifySynchronization;
import com.avances.lima.data.datasource.cloud.model.security.response.WsVerifySynchronization;
import com.avances.lima.data.datasource.cloud.model.synchronization.WsSynchronization;
import com.avances.lima.data.datasource.datastore.SynchronizationDataStore;
import com.avances.lima.domain.repository.RepositoryCallback;
import com.avances.lima.presentation.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CloudSynchronizationDataStore implements SynchronizationDataStore {
    private static final String TAG = "CloudUsuarioDataStore";

    public CloudSynchronizationDataStore() {
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

    @Override
    public void verifySynchronization(String token, String lastDateSync, RepositoryCallback repositoryCallback) {

        WsParameterVerifySynchronization wsParameterVerifySynchronization = new WsParameterVerifySynchronization();
        wsParameterVerifySynchronization.setDateLastSync("2020-03-11T23:27:18.34844");

        Call<WsVerifySynchronization> call = ApiClient.getApiClient(token).verifySynchronization(wsParameterVerifySynchronization);
        call.enqueue(new Callback<WsVerifySynchronization>() {
            @Override
            public void onResponse(Call<WsVerifySynchronization> call, Response<WsVerifySynchronization> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        WsVerifySynchronization wsVerifySynchronization = response.body();
                        if (wsVerifySynchronization.getWsResponse().getCode().equals(Constants.RESPONSE_CODES.SUCCESS)) {
                            repositoryCallback.onSuccess(wsVerifySynchronization.getWsDataVerifySynchronization().isSync());
                        } else {
                            repositoryCallback.onError(wsVerifySynchronization.getWsResponse().getMessage());
                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsVerifySynchronization> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });

    }
}
