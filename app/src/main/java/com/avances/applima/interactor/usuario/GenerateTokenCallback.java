package com.avances.applima.interactor.usuario;


public interface GenerateTokenCallback {

    void onGenerateTokenSuccess(String token);

    void onGenerateTokenError(String message);
}
