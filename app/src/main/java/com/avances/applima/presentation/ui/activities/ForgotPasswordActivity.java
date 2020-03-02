package com.avances.applima.presentation.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.avances.applima.R;
import com.avances.applima.domain.model.Usuario;
import com.avances.applima.presentation.presenter.UsuarioPresenter;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.utils.TransparentProgressDialog;
import com.avances.applima.presentation.view.UsuarioView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import butterknife.BindView;

public class ForgotPasswordActivity extends BaseActivity
        implements UsuarioView, View.OnClickListener {

    @BindView(R.id.tiEmail)
    TextInputLayout tiEmail;

    @BindView(R.id.etEmail)
    TextInputEditText etEmail;

    @BindView(R.id.ivClose)
    ImageView ivClose;

    @BindView(R.id.ivRecoveryPassword)
    ImageView ivRecoveryPassword;


    UsuarioPresenter usuarioPresenter;
    TransparentProgressDialog loading;


    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.forgot_password_activity);

        injectView();
        initUI();
        loadPresenter();
        textChangeEvents();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivClose:
                finish();
                break;
            case R.id.ivRecoveryPassword:
                String email = etEmail.getText().toString();
                if (Helper.isEmailValid(email)) {
                    if (!loading.isShowing()) {
                        loading.show();
                    }
                    usuarioPresenter.forgotPassword(Helper.getUserAppPreference(getContext()).getToken(), Constants.SYSTEM.APP, Constants.REGISTER_TYPES.EMAIL, etEmail.getText().toString());
                } else {
                    tiEmail.setError("Email no valido");
                }
                break;
        }
    }

    void loadPresenter() {
        usuarioPresenter = new UsuarioPresenter();
        usuarioPresenter.addView(this);
    }

    void initUI() {
        loading = new TransparentProgressDialog(getContext());

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ivClose.setOnClickListener(this);
        ivRecoveryPassword.setOnClickListener(this);
    }

    void textChangeEvents() {


        etEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {


                if (s.toString().length() == 0) {
                    tiEmail.setError(null);
                } else {
                    if (!Helper.isEmailValid(s.toString())) {
                        tiEmail.setError("Email no valido");
                        etEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    } else {
                        tiEmail.setError(null);
                        etEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.check_final, 0);
                    }
                }


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
              /*  if (s.length() != 0)
                    etEmail.setText("");*/
            }
        });


    }


    @Override
    public void temporalUserRegistered(String idTempUser) {

    }

    @Override
    public void tokenGenerated(String token) {

    }

    @Override
    public void userRegistered(Usuario usuario) {

    }

    @Override
    public void loginSuccess(Usuario usuario) {

    }

    @Override
    public void loginSocialMediaSuccess(Usuario usuario) {

    }

    @Override
    public void forgotPasswordSuccess(String message) {

        if (loading.isShowing()) {
            loading.dismiss();
        }
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void reSendCodeSuccess(String message) {

    }

    @Override
    public void userGot(Usuario usuario) {

    }

    @Override
    public void validateCodeSuccess(Usuario message) {

    }

    @Override
    public void routesByInterestSuccess(List<String> idRoutes) {

    }

    @Override
    public void userUpdated(Usuario usuario) {

    }

    @Override
    public void versionApp(String version) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public Context getContext() {
        return this;
    }


}