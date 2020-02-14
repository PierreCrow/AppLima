package com.avances.applima.presentation.ui.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
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
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.utils.TransparentProgressDialog;
import com.avances.applima.presentation.view.CountryView;
import com.avances.applima.presentation.view.GenderView;
import com.avances.applima.presentation.view.UsuarioView;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RegistroActivity extends BaseActivity implements UsuarioView, CountryView, GenderView {

    ImageView ivContinue, ivClose;
    Spinner spiPaises;
    UsuarioPresenter usuarioPresenter;
    TextInputEditText etEmail, etPass, etPassAgain, etNames;
    CountryPresenter countryPresenter;
    GenderPresenter genderPresenter;
    ArrayList<Country> countries;
    ArrayList<Gender> genders;
    TextView tvBirthDate;
    ImageView ivBirthDate, ivPassAgain, ivPass;
    RadioButton rbMale, rbFemale;

    String birthDay;

    boolean pickewIsViewing;

    EditText etDay,etMonth,etYear;

    boolean passView = false, passAgainView = false;
    SingleDateAndTimePickerDialog datePicker;
  //  SingleDateAndTimePickerDialog.Builder datePickerBuilder;

    TransparentProgressDialog loading;


    TextInputLayout tiEmail, tiPass, tiPassAgain, tiNames;

    final Calendar myCalendar = Calendar.getInstance();

    SingleDateAndTimePicker singleDateAndTimePicker;

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

        setContentView(R.layout.register_fragment);
        initUI();
        loadPresenter();

        clickEvents();

        textChangeEvents();
        maxLenghs();

    }


    void maxLenghs() {
        int lenghtEmail = 50;
        etEmail.setFilters(new InputFilter[]{new InputFilter.LengthFilter(lenghtEmail)});

        int lenghtPass = 20;
        etPass.setFilters(new InputFilter[]{new InputFilter.LengthFilter(lenghtPass)});
        etPassAgain.setFilters(new InputFilter[]{new InputFilter.LengthFilter(lenghtPass)});
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


        etPassAgain.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {


                if (!s.toString().equals(etPass.getText().toString())) {
                    tiPass.setError("Las contraseñas no coinciden");
                    tiPassAgain.setError("Las contraseñas no coinciden");
                } else {
                    tiPass.setError(null);
                    tiPassAgain.setError(null);
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

    void initUI() {

        pickewIsViewing=false;

        birthDay="";

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        loading = new TransparentProgressDialog(getContext());
        tiEmail = (TextInputLayout) findViewById(R.id.tiEmail);

        tiPass = (TextInputLayout) findViewById(R.id.tiPass);
        tiPassAgain = (TextInputLayout) findViewById(R.id.tiPassAgain);
        tiNames = (TextInputLayout) findViewById(R.id.tiNames);

        etEmail = (TextInputEditText) findViewById(R.id.etEmail);
        etPass = (TextInputEditText) findViewById(R.id.etPass);
        etPassAgain = (TextInputEditText) findViewById(R.id.etPassAgain);
        etNames = (TextInputEditText) findViewById(R.id.etNames);

        ivContinue = (ImageView) findViewById(R.id.ivContinue);
        ivClose = (ImageView) findViewById(R.id.ivClose);
        spiPaises = (Spinner) findViewById(R.id.spiPaises);
        tvBirthDate = (TextView) findViewById(R.id.tvBirthDate);
        ivBirthDate = (ImageView) findViewById(R.id.ivBirthDate);

        ivPassAgain = (ImageView) findViewById(R.id.ivPassAgain);
        ivPass = (ImageView) findViewById(R.id.ivPass);

        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);

        etDay=(EditText)findViewById(R.id.etDay);
        etMonth=(EditText)findViewById(R.id.etMonth);
        etYear=(EditText)findViewById(R.id.etYear);


      //  datePicker=(SingleDateAndTimePicker)findViewById(R.id.ios);


     /*   singleDateAndTimePicker = (SingleDateAndTimePicker) findViewById(R.id.ios);
        singleDateAndTimePicker.addOnDateChangedListener(new SingleDateAndTimePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(String displayed, Date date) {


                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                String dateComplete=sdf.format(date);

                String day=dateComplete.substring(0,2);
                String month=dateComplete.substring(3,5);
                String year=dateComplete.substring(6,10);


                etDay.setText(day);
                etMonth.setText(month);
                etYear.setText(year);

            }
        });
*/
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        String dateComplete=sdf.format(myCalendar.getTime());

        String day=dateComplete.substring(0,2);
        String month=dateComplete.substring(3,5);
        String year=dateComplete.substring(6,10);


        etDay.setText(day);
        etMonth.setText(month);
        etYear.setText(year);

        tvBirthDate.setText(dateComplete);
    }


    void clickEvents() {
        ivContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String day=etDay.getText().toString();
                String month=etMonth.getText().toString();
                String year=etYear.getText().toString();


                birthDay=day+"/"+month+"/"+year;

                String email = etEmail.getText().toString();
                String pass = etPass.getText().toString();
                String name = etNames.getText().toString();
                String idTemporal = Helper.getUserAppPreference(getContext()).getIdTemporal();
              //  String birthDay = tvBirthDate.getText().toString();
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


                if (Helper.isEmailValid(email)) {

                    if (etPass.getText().length() > 5) {

                        if (etPassAgain.getText().length() > 5) {

                            if (!loading.isShowing()) {
                                loading.show();
                            }
                            usuarioPresenter.registerUser(Helper.getUserAppPreference(getContext()).getToken(),name, birthDay, sex, country, email, pass, idTemporal, Constants.REGISTER_TYPES.EMAIL, Constants.SYSTEM.APP);
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Completa los datos correctamente", Toast.LENGTH_SHORT);
                            toast.setMargin(50, 50);
                            toast.show();
                        }
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Completa los datos correctamente", Toast.LENGTH_SHORT);
                        toast.setMargin(50, 50);
                        toast.show();
                    }

                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Completa los datos correctamente", Toast.LENGTH_SHORT);
                    toast.setMargin(50, 50);
                    toast.show();
                }


            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        ivBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

           /*     new DatePickerDialog(RegistroActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
*/


           if(pickewIsViewing)
           {
               //singleDateAndTimePicker.setVisibility(View.GONE);
               //datePicker.setVisibility(View.GONE);
           }
           else
           {
              // singleDateAndTimePicker.setVisibility(View.VISIBLE);
             //  datePicker.display();
              // loadDateIOS();

               loadDateIOS();

           }



              //  loadDateIOS();
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

        ivPassAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!passAgainView) {
                    etPassAgain.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passAgainView = true;
                } else {
                    etPassAgain.setTransformationMethod(null);
                    passAgainView = false;
                }
            }
        });

    }


    void loadDateIOS()
    {



        new SingleDateAndTimePickerDialog.Builder(this)
                .bottomSheet()
                .curved()
                .displayMinutes(false)
                .displayHours(false)
                .displayDays(false)
                .displayMonth(true)
                .displayYears(true)
                .displayDays(true)
                .listener(new SingleDateAndTimePickerDialog.Listener() {
                    @Override
                    public void onDateSelected(Date date) {

                        String myFormat = "dd/MM/yyyy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                        String dateComplete=sdf.format(date);
                        birthDay=dateComplete;

                        String day=dateComplete.substring(0,2);
                        String month=dateComplete.substring(3,5);
                        String year=dateComplete.substring(6,10);
                        etDay.setText(day);
                        etMonth.setText(month);
                        etYear.setText(year);

                        tvBirthDate.setText(dateComplete);
                    }
                })
                .display();

    }

    @Override
    public void temporalUserRegistered(String idTempUser) {

    }

    @Override
    public void tokenGenerated(String token) {

    }

    @Override
    public void userRegistered(Usuario usuario) {


        UserPreference userPreference= Helper.getUserAppPreference(getContext());
        userPreference.setEmail(usuario.getEmail());
        Helper.saveUserAppPreference(getContext(),userPreference);

        next(ValidationActivity.class, null);

        if (loading.isShowing()) {
            loading.dismiss();
        }

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