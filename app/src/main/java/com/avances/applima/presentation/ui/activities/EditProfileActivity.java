package com.avances.applima.presentation.ui.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.avances.applima.R;
import com.avances.applima.data.mapper.UsuarioDataMapper;
import com.avances.applima.domain.model.Country;
import com.avances.applima.domain.model.Gender;
import com.avances.applima.domain.model.UserPreference;
import com.avances.applima.domain.model.Usuario;
import com.avances.applima.presentation.presenter.CountryPresenter;
import com.avances.applima.presentation.presenter.GenderPresenter;
import com.avances.applima.presentation.presenter.UsuarioPresenter;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.view.CountryView;
import com.avances.applima.presentation.view.GenderView;
import com.avances.applima.presentation.view.UsuarioView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class EditProfileActivity extends BaseActivity implements CountryView, UsuarioView, GenderView {

    ImageView ivBack, ivUpdate;
    ImageView ivContinue, ivClose;
    EditText etUserEmail, etUserPassword, etUserPhone;
    UserPreference userPreference;

    TextInputEditText etEmail, etPass, etNames;
    CountryPresenter countryPresenter;
    UsuarioPresenter usuarioPresenter;
    TextInputLayout tiEmail, tiPass, tiNames;
    ImageView ivPass, ivBirthDate;
    ArrayList<Country> countries;
    Spinner spiPaises;
    RadioButton rbMale, rbFemale;
    TextView tvBirthDate;
    ArrayList<Gender> genders;
    GenderPresenter genderPresenter;

    final Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };


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

        genderPresenter = new GenderPresenter();
        genderPresenter.addView(this);
        genderPresenter.getGenders(Constants.STORE.DB);

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

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tvBirthDate.setText(sdf.format(myCalendar.getTime()));
    }


    void initUI() {


/*
        etUserEmail = (EditText) findViewById(R.id.etUserEmail);
        etUserPassword = (EditText) findViewById(R.id.etUserPassword);
        etUserPhone = (EditText) findViewById(R.id.etphone);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivUpdate = (ImageView) findViewById(R.id.ivUpdate);*/

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        tiEmail = (TextInputLayout) findViewById(R.id.tiEmail);
        tiPass = (TextInputLayout) findViewById(R.id.tiPass);
        tiNames = (TextInputLayout) findViewById(R.id.tiNames);

        etEmail = (TextInputEditText) findViewById(R.id.etEmail);
        etPass = (TextInputEditText) findViewById(R.id.etPass);
        etNames = (TextInputEditText) findViewById(R.id.etNames);
        //etUserPhone = (EditText) findViewById(R.id.etphone);

        ivPass = (ImageView) findViewById(R.id.ivPass);
        ivContinue = (ImageView) findViewById(R.id.ivContinue);
        ivBack = (ImageView) findViewById(R.id.ivClose);

        spiPaises = (Spinner) findViewById(R.id.spiPaises);

        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);
        ivBirthDate = (ImageView) findViewById(R.id.ivBirthDate);
        tvBirthDate = (TextView) findViewById(R.id.tvBirthDate);
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

                String email = etEmail.getText().toString();
                String name = etNames.getText().toString();
                String birthDay = tvBirthDate.getText().toString();
                String country = "";
                String sex = "";
                String countrySelected = spiPaises.getSelectedItem().toString();

                for (Country pais : countries) {
                    if (countrySelected.equals(pais.getDetailParameterValue())) {
                        country = pais.getId();
                    }
                }

                String sexSelected = "";

                if (rbMale.isChecked()) {
                    sexSelected = "M";
                } else {
                    if (rbFemale.isChecked()) {
                        sexSelected = "F";
                    }
                }
                for (Gender sexo : genders) {
                    if (sexSelected.equals(sexo.getNameParameterValue())) {
                        sex = sexo.getId();
                    }
                }

                usuarioPresenter.updateUser(Helper.getUserAppPreference(getContext()).getToken(), name, birthDay, sex, country, email, Helper.getUserAppPreference(getContext()).getPass(), Helper.getUserAppPreference(getContext()).getRegisterLoginType(), Constants.SYSTEM.APP);


            }
        });
    }

    void setFields() {


        userPreference = Helper.getUserAppPreference(getApplicationContext());

        etEmail.setText(userPreference.getEmail());
        //   etUserPhone.setText(userPreference.getPhone());
        etNames.setText(userPreference.getName());
        if (userPreference.getRegisterLoginType().equals(Constants.REGISTER_TYPES.EMAIL)) {
            etPass.setText(userPreference.getPass());
        } else {
            etPass.setVisibility(View.GONE);
            tiPass.setVisibility(View.GONE);
        }

        etEmail.setEnabled(false);

        if (!userPreference.getCountry().equals("")) {
            for (int i = 0; i < countries.size(); i++) {
                if (userPreference.getCountry().equals(countries.get(i).getId())) {
                    spiPaises.setSelection(i);
                }
            }
        }


        if(userPreference.getBirthDate().equals("01/01/0001"))
        {
            tvBirthDate.setText("");
        }
        else
        {
            tvBirthDate.setText(userPreference.getBirthDate());
        }


        if (!userPreference.getGender().equals("")) {
            if (userPreference.getGender().equals("SEXO001")) {
                rbMale.setChecked(true);
            } else {
                if (userPreference.getGender().equals("SEXO002")) {
                    rbFemale.setChecked(true);
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

      //  UsuarioDataMapper usuarioDataMapper = new UsuarioDataMapper();

        UserPreference userPreference = Helper.getUserAppPreference(getApplicationContext());
        userPreference.setName(usuario.getName());
        userPreference.setGender(usuario.getSex());
        userPreference.setCountry(usuario.getCountry());
        userPreference.setBirthDate(usuario.getBirthDate());

        Helper.saveUserAppPreference(getApplicationContext(), userPreference);

        setFields();

        Toast toast=Toast. makeText(getApplicationContext(),"Se actualizaron sus datos", Toast. LENGTH_SHORT);
        toast. setMargin(50,50);
        toast. show();
    }

    @Override
    public void genderListLoaded(List<Gender> mGenders) {
        genders = (ArrayList<Gender>) mGenders;
    }

    @Override
    public void genderCreated(String message) {

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