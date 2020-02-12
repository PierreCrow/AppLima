package com.avances.applima.interactor.usuario;


public interface ForgotPasswordCallback {

    void onForgotPasswordSuccess(String message);

    void onForgotPasswordError(String message);
}
