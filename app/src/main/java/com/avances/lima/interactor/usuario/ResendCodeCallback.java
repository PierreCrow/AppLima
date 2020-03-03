package com.avances.lima.interactor.usuario;


public interface ResendCodeCallback {

    void onResendCodeSuccess(String message);

    void onResendCodeError(String message);
}
