package com.avances.applima.interactor.usuario;


public interface ResendCodeCallback {

    void onResendCodeSuccess(String message);

    void onResendCodeError(String message);
}
