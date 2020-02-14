package com.avances.applima.presentation.ui.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class CompleteInfoActivity extends BaseActivity implements UsuarioView, CountryView, GenderView {

    ImageView ivContinue,ivClose;
    Spinner spiPaises;
    UsuarioPresenter usuarioPresenter;
    ImageView btnContinue;

    TextInputEditText etEmail, etNames;
    TextInputLayout tiEmail, tiNames;
    CountryPresenter countryPresenter;
    GenderPresenter genderPresenter;
    ArrayList<Country> countries;
    ArrayList<Gender> genders;
    ImageView ivBirthDate;
    TextView tvBirthDate;
    RadioButton rbMale, rbFemale;
    TransparentProgressDialog loading;

    EditText etDay,etMonth,etYear;
    String birthDay;


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

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tvBirthDate.setText(sdf.format(myCalendar.getTime()));
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.complete_info_fragment);
        initUI();
        loadPresenter();

        clickEvents();
        textChangeEvents();
        maxLenghs();

        setFields();

    }


    void setFields()
    {
        UserPreference userPreference= Helper.getUserAppPreference(getContext());

        etEmail.setText(userPreference.getEmail());
        etNames.setText(userPreference.getName());
     //   tvBirthDate.setText(userPreference.getBirthDate());


        if(!userPreference.getCountry().equals("")) {
            for (int i = 0; i < countries.size(); i++) {
                if (userPreference.getCountry().equals(countries.get(i).getId())) {
                    spiPaises.setSelection(i);
                }
            }
        }


        if(!userPreference.getGender().equals("")) {
            if (userPreference.getGender().equals("M")) {
                rbMale.setChecked(true);
            } else {
                if (userPreference.getGender().equals("F")) {
                    rbFemale.setChecked(true);
                }
            }
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
              /*  if (s.length() != 0)
                    etEmail.setText("");*/
            }
        });



    }

    void loadPresenter() {
        usuarioPresenter = new UsuarioPresenter();
        usuarioPresenter.addView(this);

        countryPresenter = new CountryPresenter();
        countryPresenter.addView(this);
        countryPresenter.getCountries(Constants.STORE.DB);

        genderPresenter = new GenderPresenter();
        genderPresenter.addView(this);
        genderPresenter.getGenders(Constants.STORE.DB);
    }

    void maxLenghs() {
        int lenghtEmail = 50;
        etEmail.setFilters(new InputFilter[]{new InputFilter.LengthFilter(lenghtEmail)});

    }

    void initUI()
    {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        loading = new TransparentProgressDialog(getContext());
        btnContinue=(ImageView)findViewById(R.id.ivContinue);

        etEmail=(TextInputEditText)findViewById(R.id.etEmail) ;
        etNames=(TextInputEditText)findViewById(R.id.etNames) ;

        tiEmail = (TextInputLayout) findViewById(R.id.tiEmail);
        tiNames = (TextInputLayout) findViewById(R.id.tiNames);

        ivContinue = (ImageView) findViewById(R.id.ivContinue);
        spiPaises=(Spinner)findViewById(R.id.spiPaises);

        tvBirthDate = (TextView) findViewById(R.id.tvBirthDate);
        ivBirthDate = (ImageView) findViewById(R.id.ivBirthDate);

        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);

        etDay=(EditText)findViewById(R.id.etDay);
        etMonth=(EditText)findViewById(R.id.etMonth);
        etYear=(EditText)findViewById(R.id.etYear);
    }


    void clickEvents()
    {
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=etEmail.getText().toString();
                String name=etNames.getText().toString();
                 birthDay = tvBirthDate.getText().toString();
                String country = "";
                String sex = "";
                String idTemporal = Helper.getUserAppPreference(getContext()).getIdTemporal();


                String day=etDay.getText().toString();
                String month=etMonth.getText().toString();
                String year=etYear.getText().toString();

                birthDay=day+"/"+month+"/"+year;

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

                if (Helper.isEmailValid(email)) {

                    //UPDATE USER
                    if (!loading.isShowing()) {
                        loading.show();
                    }


                    if(Integer.parseInt(day)>12)
                    {
                        birthDay="";
                    }

                    if(Integer.parseInt(year)>2020)
                    {
                        birthDay="";
                    }

                    usuarioPresenter.updateUser(Helper.getUserAppPreference(getContext()).getToken(),name,birthDay,sex,country,email,Helper.getUserAppPreference(getContext()).getPass(),Helper.getUserAppPreference(getContext()).getRegisterLoginType(),Constants.SYSTEM.APP);

                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Completa los datos correctamente", Toast.LENGTH_SHORT);
                    toast.setMargin(50, 50);
                    toast.show();
                }


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

        UserPreference userPreference = Helper.getUserAppPreference(getApplicationContext());
        userPreference.setRegisterLoginType(usuario.getRegisterType());
        userPreference.setName(usuario.getName());
        userPreference.setEmail(usuario.getEmail());
        userPreference.setId(usuario.getIdCloud());
        userPreference.setCountry(usuario.getCountry());
        userPreference.setGender(usuario.getSex());
        userPreference.setBirthDate(usuario.getBirthDate());
        userPreference.setLogged(true);
        Helper.saveUserAppPreference(getApplicationContext(), userPreference);

        if (loading.isShowing()) {
            loading.dismiss();
        }

        next(MainActivity.class,null);

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