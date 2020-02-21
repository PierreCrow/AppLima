package com.avances.applima.presentation.ui.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
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
import com.avances.applima.domain.model.Country;
import com.avances.applima.domain.model.Gender;
import com.avances.applima.domain.model.UserPreference;
import com.avances.applima.domain.model.Usuario;
import com.avances.applima.presentation.presenter.CountryPresenter;
import com.avances.applima.presentation.presenter.GenderPresenter;
import com.avances.applima.presentation.presenter.UsuarioPresenter;
import com.avances.applima.presentation.ui.fragments.TabHome;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.utils.TransparentProgressDialog;
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

import butterknife.BindView;

public class EditProfileActivity extends BaseActivity
        implements CountryView, UsuarioView, GenderView, View.OnClickListener {

    @BindView(R.id.ivClose)
    ImageView ivBack;

    @BindView(R.id.ivClose)
    ImageView ivContinue;

    @BindView(R.id.ivClose)
    ImageView ivClose;

    @BindView(R.id.ivClose)
    TextInputEditText etEmail;

    @BindView(R.id.etPass)
    TextInputEditText etPass;

    @BindView(R.id.etNames)
    TextInputEditText etNames;

    @BindView(R.id.tiEmail)
    TextInputLayout tiEmail;

    @BindView(R.id.tiPass)
    TextInputLayout tiPass;

    @BindView(R.id.tiNames)
    TextInputLayout tiNames;

    @BindView(R.id.ivPass)
    ImageView ivPass;

    @BindView(R.id.ivBirthDate)
    ImageView ivBirthDate;

    @BindView(R.id.spiPaises)
    Spinner spiPaises;

    @BindView(R.id.rbMale)
    RadioButton rbMale;

    @BindView(R.id.rbFemale)
    RadioButton rbFemale;

    @BindView(R.id.tvBirthDate)
    TextView tvBirthDate;

    @BindView(R.id.etDay)
    EditText etDay;

    @BindView(R.id.etMonth)
    EditText etMonth;

    @BindView(R.id.etDay)
    EditText etYear;


    UserPreference userPreference;
    CountryPresenter countryPresenter;
    UsuarioPresenter usuarioPresenter;
    ArrayList<Country> countries;
    ArrayList<Gender> genders;
    GenderPresenter genderPresenter;
    String birthDay;
    TransparentProgressDialog loading;

    boolean passView = false;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_profile_activity);

        injectView();

        initUI();

        loadPresenter();
        setFields();

        textchangeListener();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ivPass:
                if (!passView) {
                    etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passView = true;
                } else {
                    etPass.setTransformationMethod(null);
                    passView = false;
                }
                break;
            case R.id.ivBack:
                finish();

                TabHome.real_Value = true;

                SharedPreferences preferenciasssee = getContext().getSharedPreferences("Preference_Profile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferenciasssee.edit();
                editor.putBoolean("BackfromProfile", true);
                editor.commit();
                next(MainActivity.class, null);
                break;
            case R.id.ivContinue:
                clickContinue();
                break;
        }
    }


    void clickContinue() {
        String email = etEmail.getText().toString();
        String name = etNames.getText().toString();
        birthDay = tvBirthDate.getText().toString();
        String country = "";
        String sex = "";
        String countrySelected = spiPaises.getSelectedItem().toString();


        String day = etDay.getText().toString();
        String month = etMonth.getText().toString();
        String year = etYear.getText().toString();
        birthDay = day + "/" + month + "/" + year;


        if (birthDay.equals("//")) {
            birthDay = "";
        }


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
                sex = sexo.getId();
            }
        }


        if (Integer.parseInt(day) > 31) {
            birthDay = "";
            Toast.makeText(getApplicationContext(), "Ingrese un día correcto", Toast.LENGTH_SHORT).show();

        } else {
            if (Integer.parseInt(month) > 12) {
                birthDay = "";
                Toast.makeText(getApplicationContext(), "Ingrese un mes correcto", Toast.LENGTH_SHORT).show();
            } else {
                if (Integer.parseInt(year) > 2020) {
                    birthDay = "";
                    Toast.makeText(getApplicationContext(), "Ingrese un año correcto", Toast.LENGTH_SHORT).show();
                } else {
                    if (!loading.isShowing()) {
                        loading.show();
                    }


                    SharedPreferences preferenciasssee = getContext().getSharedPreferences("Preference_Pass", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editoriieei = preferenciasssee.edit();
                    editoriieei.putString("Pass", etPass.getText().toString());
                    editoriieei.apply();

                    usuarioPresenter.updateUser(Helper.getUserAppPreference(getContext()).getToken(), name, birthDay, sex, country, email, Helper.getUserAppPreference(getContext()).getPass(), Helper.getUserAppPreference(getContext()).getRegisterLoginType(), Constants.SYSTEM.APP);

                }
            }
        }

    }


    void initUI() {

        loading = new TransparentProgressDialog(getContext());

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ivPass.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ivContinue.setOnClickListener(this);

 /*
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

        etDay = (EditText) findViewById(R.id.etDay);
        etMonth = (EditText) findViewById(R.id.etMonth);
        etYear = (EditText) findViewById(R.id.etYear);
        */
    }


    void textchangeListener() {
        etDay.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etDay.setError(null);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() == 2) {
                    etMonth.requestFocus();
                }

            }
        });

        etMonth.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etMonth.setError(null);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() == 2) {
                    etYear.requestFocus();
                }
            }
        });

        etYear.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etYear.setError(null);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public void SeteaSpinner(ArrayList<Country> mis_afectas, Spinner spiner, Context ctx) {
        final List<String> afectaciones = new ArrayList<String>();// = new ArrayList<>(Arrays.asList(RubroNegocio_array));
        //afectaciones.add("Seleccione");


        for (Integer i = 0; i < mis_afectas.size(); i++) {
            //  String temp=mis_afectas.get(i).getDetailParameterValue();
            //  String nickname = temp.substring(0, temp.indexOf(' '));
            afectaciones.add(mis_afectas.get(i).getNameParameterValue());
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


    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tvBirthDate.setText(sdf.format(myCalendar.getTime()));
    }


    void setFields() {

        SharedPreferences preferences = getContext().getSharedPreferences("Preference_Pass", Context.MODE_PRIVATE);
        String contraaa = preferences.getString("Pass", "");

        userPreference = Helper.getUserAppPreference(getApplicationContext());

        etEmail.setText(userPreference.getEmail());
        //   etUserPhone.setText(userPreference.getPhone());
        etNames.setText(userPreference.getName());
        if (userPreference.getRegisterLoginType().equals(Constants.REGISTER_TYPES.EMAIL)) {
            // etPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            etPass.setText(contraaa);
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


        if (userPreference.getBirthDate().equals("01/01/0001")) {
            tvBirthDate.setText("");
        } else {
            //  tvBirthDate.setText(userPreference.getBirthDate());

            String cunple = userPreference.getBirthDate();

            if (cunple != null) {

                if (cunple.length() == 0) {
                } else {
                    String day = cunple.substring(0, 2);
                    String month = cunple.substring(3, 5);
                    String year = cunple.substring(6, 10);

                    etDay.setText(day);
                    etMonth.setText(month);
                    etYear.setText(year);
                }

            } else {
            }

        }

        if (!userPreference.getGender().equals("")) {
            if (userPreference.getGender().equals("SEXO0001")) {
                rbMale.setChecked(true);
            } else {
                if (userPreference.getGender().equals("SEXO0002")) {
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
    public void routesByInterestSuccess(List<String> idRoutes) {

    }

    @Override
    public void userUpdated(Usuario usuario) {

        //  UsuarioDataMapper usuarioDataMapper = new UsuarioDataMapper();


        if (loading.isShowing()) {
            loading.dismiss();
        }

        UserPreference userPreference = Helper.getUserAppPreference(getContext());
        userPreference.setName(usuario.getName());
        userPreference.setGender(usuario.getSex());
        userPreference.setCountry(usuario.getCountry());
        userPreference.setBirthDate(usuario.getBirthDate());

        Helper.saveUserAppPreference(getContext(), userPreference);

        setFields();

        Toast.makeText(getApplicationContext(), "Se actualizaron sus datos", Toast.LENGTH_SHORT).show();


        TabHome.tabLayout.getTabAt(3).select();

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