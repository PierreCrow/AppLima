package com.avances.lima.interactor.usuario;


public interface UploadImageCallback {

    void onUploadImageSuccess(String message);

    void onUploadImageError(String message);
}
