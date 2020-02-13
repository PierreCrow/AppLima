package com.avances.applima.presentation.ui.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.avances.applima.R;
import com.avances.applima.domain.model.Country;
import com.avances.applima.domain.model.UserPreference;
import com.avances.applima.domain.model.Usuario;
import com.avances.applima.presentation.presenter.CountryPresenter;
import com.avances.applima.presentation.presenter.UsuarioPresenter;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.view.CountryView;
import com.avances.applima.presentation.view.UsuarioView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends BaseActivity implements CountryView, UsuarioView {

    ImageView ivBack, ivUpdate;
    ImageView ivContinue, ivClose;
    EditText etUserEmail, etUserPassword, etUserPhone;
    UserPreference userPreference;

    TextInputEditText etEmail, etPass,  etNames;
    CountryPresenter countryPresenter;
    UsuarioPresenter usuarioPresenter;
    TextInputLayout tiEmail, tiPass,  tiNames;
    ImageView  ivPass;
    ArrayList<Country> countries;
    Spinner spiPaises;

    public void SeteaSpinner(ArrayList<Country> mis_afectas, Spinner spiner, Context ctx) {
        final List<String> afectaciones = new ArrayList<String>();// = new ArrayList<>(Arrays.asList(RubroNegocio_array));
        //afectaciones.add("Seleccione");


        for (Integer i = 0; i < mis_afectas.size(); i++) {
            //  String temp=mis_afectas.get(i).getDetailParameterValue();
            //  String nickname = temp.substring(0, temp.indexOf(' '));
            afectaciones.add(mis_afectas.get(i).getDetailParameterValue());
        }

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                ctx, R.layout.spinneritem, afectaciones) {
            @Override
            public boolean isEnabled(int position) {

                return true;

            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                tv.setTextColor(Color.BLACK);

                return view;
            }
        };

        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinneritem);
        spiner.setAdapter(spinnerArrayAdapter1);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    void loadPresenter() {
        usuarioPresenter = new UsuarioPresenter();
        usuarioPresenter.addView(this);

        countryPresenter = new CountryPresenter();
        countryPresenter.addView(this);
        countryPresenter.getCountries(Constants.STORE.DB);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_profile_activity);

        initUI();
        clickEvents();
        loadPresenter();
        setFields();

    }

    void initUI() {

        userPreference = Helper.getUserAppPreference(getApplicationContext());
/*
        etUserEmail = (EditText) findViewById(R.id.etUserEmail);
        etUserPassword = (EditText) findViewById(R.id.etUserPassword);
        etUserPhone = (EditText) findViewById(R.id.etUserPhone);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivUpdate = (ImageView) findViewById(R.id.ivUpdate);*/

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        tiEmail = (TextInputLayout) findViewById(R.id.tiEmail);
        tiPass = (TextInputLayout) findViewById(R.id.tiPass);
        tiNames = (TextInputLayout) findViewById(R.id.tiNames);

        etEmail = (TextInputEditText) findViewById(R.id.etEmail);
        etPass = (TextInputEditText) findViewById(R.id.etPass);
        etNames = (TextInputEditText) findViewById(R.id.etNames);

        ivPass = (ImageView) findViewById(R.id.ivPass);
        ivContinue = (ImageView) findViewById(R.id.ivContinue);
        ivBack = (ImageView) findViewById(R.id.ivBack);

        spiPaises = (Spinner) findViewById(R.id.spiPaises);
    }


    void clickEvents() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        ivContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

    void setFields() {
        etUserEmail.setText(userPreference.getEmail());
        etUserPhone.setText(userPreference.getPhone());
        etNames.setText(userPreference.getName());
        if (userPreference.getRegisterLoginType().equals( Constants.REGISTER_TYPES.EMAIL)) {
            etUserPassword.setText(userPreference.getPass());
        } else {
            etUserPassword.setEnabled(false);
        }

        etEmail.setEnabled(false);

        if(!userPreference.getCountry().equals("")) {
            for (int i = 0; i < countries.size(); i++) {
                if (userPreference.getCountry().equals(countries.get(i).getId())) {
                    spiPaises.setSelection(i);
                }
            }
        }
    }


    @Override
    public void countryListLoaded(List<Country> mCountries) {
        countries = (ArrayList<Country>) mCountries;
        SeteaSpinner(countries, spiPaises, getApplicationContext());
    }

    @Override
    public void countryCreated(String message) {

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

    }

    @Override
    public void reSendCodeSuccess(String message) {

    }

    @Override
    public void userGot(Usuario usuario) {

    }

    @Override
    public void validateCodeSuccess(Usuario usuario) {

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