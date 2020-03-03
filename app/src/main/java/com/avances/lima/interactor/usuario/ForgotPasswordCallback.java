package com.avances.lima.interactor.usuario;


public interface ForgotPasswordCallback {

    void onForgotPasswordSuccess(String message);

    void onForgotPasswordError(String message);
}
