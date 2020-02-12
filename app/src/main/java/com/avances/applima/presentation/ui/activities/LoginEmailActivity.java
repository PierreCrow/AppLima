package com.avances.applima.presentation.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avances.applima.R;
import com.avances.applima.domain.model.Usuario;
import com.avances.applima.presentation.presenter.UsuarioPresenter;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.view.UsuarioView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginEmailActivity extends BaseActivity implements UsuarioView {

    ImageView ivClose, ivContinue;
    TextView tvOlvidasteContrasena;
    UsuarioPresenter usuarioPresenter;


    TextInputLayout tiEmail, tiPass;
    TextInputEditText etEmail, etPass;
    boolean passView = false;
    ImageView ivPass;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_email_activity);

        loadPresenter();
        initUI();
        clickEvents();

        textChangeEvents();
        maxLenghs();

    }

    void loadPresenter() {
        usuarioPresenter = new UsuarioPresenter();
        usuarioPresenter.addView(this);
    }

    void initUI() {
        ivClose = (ImageView) findViewById(R.id.ivClose);
        ivContinue = (ImageView) findViewById(R.id.ivContinue);
        tvOlvidasteContrasena = (TextView) findViewById(R.id.tvOlvidasteContrasena);

        tiEmail = (TextInputLayout) findViewById(R.id.tiEmail);
        tiPass = (TextInputLayout) findViewById(R.id.tiPass);
        etEmail = (TextInputEditText) findViewById(R.id.etEmail);
        etPass = (TextInputEditText) findViewById(R.id.etPass);

        ivPass = (ImageView) findViewById(R.id.ivPass);

    }


    void clickEvents() {
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


        ivContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString();
                String pass = etPass.getText().toString();


                if (Helper.isEmailValid(email)) {

                    if (etPass.getText().length() > 5) {
                        usuarioPresenter.login(Helper.getUserAppPreference(getContext()).getToken(),email, pass, Constants.SYSTEM.APP, Constants.REGISTER_TYPES.EMAIL);
                    } else {
                        tiPass.setError("Minimo 6 caracteres");
                    }

                } else {
                    tiEmail.setError("Email no valido");
                }


            }
        });

        tvOlvidasteContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                next(OlvidarContrasenaActivity.class, null);

            }
        });

        ivPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!passView) {
                    etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passView = true;
                } else {
                    etPass.setTransformationMethod(null);
                    passView = false;
                }

            }
        });

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

        //validar si mandamos a completar datops o al home
        if(usuario.getRegisterState().equals("ESRE0001"))
        {
            next(CompleteInfoActivity.class,null);
        }
        else
        {
            next(MainActivity.class,null);
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
    public void userUpdated(Usuario usuario) {

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