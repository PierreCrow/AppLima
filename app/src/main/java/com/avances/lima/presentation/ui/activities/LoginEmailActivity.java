package com.avances.lima.presentation.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;

import com.avances.lima.R;
import com.avances.lima.domain.model.UserPreference;
import com.avances.lima.domain.model.Usuario;
import com.avances.lima.presentation.presenter.UsuarioPresenter;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.utils.SingleClick;
import com.avances.lima.presentation.utils.TransparentProgressDialog;
import com.avances.lima.presentation.view.UsuarioView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import butterknife.BindView;

public class LoginEmailActivity extends BaseActivity
        implements UsuarioView {

    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.ivContinue)
    ImageView ivContinue;
    @BindView(R.id.tvOlvidasteContrasena)
    AppCompatTextView tvOlvidasteContrasena;
    @BindView(R.id.tiEmail)
    TextInputLayout tiEmail;
    @BindView(R.id.tiPass)
    TextInputLayout tiPass;
    @BindView(R.id.etEmail)
    TextInputEditText etEmail;
    @BindView(R.id.etPass)
    TextInputEditText etPass;
    @BindView(R.id.ivPass)
    ImageView ivPass;

    TransparentProgressDialog loading;
    UsuarioPresenter usuarioPresenter;
    boolean passView = false;
    SingleClick singleClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_email_activity);
        injectView();
        loadPresenter();
        initUI();
        textChangeEvents();
        maxLenghs();
    }

    void loadPresenter() {
        usuarioPresenter = new UsuarioPresenter();
        usuarioPresenter.addView(this);
    }

    void initUI() {
        onClickListener();
        ivPass.setOnClickListener(singleClick);
        tvOlvidasteContrasena.setOnClickListener(singleClick);
        ivClose.setOnClickListener(singleClick);
        ivContinue.setOnClickListener(singleClick);
        loading = new TransparentProgressDialog(getContext());
    }

    private void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {
                switch (v.getId()) {
                    case R.id.ivClose:
                        finish();
                        break;
                    case R.id.ivContinue:
                        clickContinue();
                        break;
                    case R.id.tvOlvidasteContrasena:
                        next(ForgotPasswordActivity.class, null);
                        break;
                    case R.id.ivPass:
                        clickViewPass();
                        break;
                }
            }
        };
    }

    void maxLenghs() {
        int lenghtEmail = 50;
        etEmail.setFilters(new InputFilter[]{new InputFilter.LengthFilter(lenghtEmail)});
        int lenghtPass = 20;
        etPass.setFilters(new InputFilter[]{new InputFilter.LengthFilter(lenghtPass)});
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    void clickViewPass() {
        if (!passView) {
            etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            passView = true;
        } else {
            etPass.setTransformationMethod(null);
            passView = false;
        }
    }

    void clickContinue() {
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        String passEnconded = "";
        try {
            passEnconded = Helper.hash256(pass);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (Helper.isEmailValid(email)) {

            if (etPass.getText().length() > 5) {
                if (!loading.isShowing()) {
                    loading.show();
                }
                usuarioPresenter.login(Helper.getUserAppPreference(getContext()).getToken(), email, passEnconded, Constants.SYSTEM.APP, Constants.REGISTER_TYPES.EMAIL);
            } else {
                tiPass.setError("Minimo 6 caracteres");
            }
        } else {
            tiEmail.setError("Email no valido");
        }
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
            }
        });

        etPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() < 6) {
                    tiPass.setError("Minimo 6 caracteres");
                } else {
                    tiPass.setError(null);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
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
        UserPreference userPreference = Helper.getUserAppPreference(getContext());
        userPreference.setName(usuario.getName());
        userPreference.setEmail(usuario.getEmail());
        userPreference.setName(usuario.getName());
        userPreference.setLogged(true);
        userPreference.setSecondsToOfferViewed(true);
        userPreference.setCountry(usuario.getCountry());
        userPreference.setGender(usuario.getSex());
        userPreference.setBirthDate(usuario.getBirthDate());
        userPreference.setRegisterLoginType(usuario.getRegisterType());
        userPreference.setImage(usuario.getImage());
        Helper.saveUserAppPreference(getContext(), userPreference);

        if (usuario.getRegisterState().equals(Constants.RESPONSE_CODES.USER_CODE_NOT_REGISTERED)) {
            next(CompleteInfoActivity.class, null);
            if (loading.isShowing()) {
                loading.dismiss();
            }
        } else {
            next(MainActivity.class, null);

            if (loading.isShowing()) {
                loading.dismiss();
            }
        }
    }

    @Override
    public void loginSocialMediaSuccess(Usuario usuario) {
    }

    @Override
    public void forgotPasswordSuccess(String message) {
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
    public void imageUploaded(String message) {
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showErrorMessage(String message) {
        if (loading.isShowing()) {
            loading.dismiss();
        }
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}