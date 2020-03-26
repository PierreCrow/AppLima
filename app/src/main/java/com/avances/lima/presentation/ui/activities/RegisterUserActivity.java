package com.avances.lima.presentation.ui.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
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

import com.avances.lima.R;
import com.avances.lima.domain.model.Country;
import com.avances.lima.domain.model.Gender;
import com.avances.lima.domain.model.UserPreference;
import com.avances.lima.domain.model.Usuario;
import com.avances.lima.presentation.presenter.CountryPresenter;
import com.avances.lima.presentation.presenter.GenderPresenter;
import com.avances.lima.presentation.presenter.UsuarioPresenter;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.utils.SingleClick;
import com.avances.lima.presentation.utils.TransparentProgressDialog;
import com.avances.lima.presentation.view.CountryView;
import com.avances.lima.presentation.view.GenderView;
import com.avances.lima.presentation.view.UsuarioView;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

public class RegisterUserActivity extends BaseActivity
        implements UsuarioView, CountryView, GenderView {

    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.ivContinue)
    ImageView ivContinue;
    @BindView(R.id.spiPaises)
    Spinner spiPaises;
    @BindView(R.id.etEmail)
    TextInputEditText etEmail;
    @BindView(R.id.etPass)
    TextInputEditText etPass;
    @BindView(R.id.etPassAgain)
    TextInputEditText etPassAgain;
    @BindView(R.id.etNames)
    TextInputEditText etNames;
    @BindView(R.id.tiEmail)
    TextInputLayout tiEmail;
    @BindView(R.id.tiPass)
    TextInputLayout tiPass;
    @BindView(R.id.tiPassAgain)
    TextInputLayout tiPassAgain;
    @BindView(R.id.tiNames)
    TextInputLayout tiNames;
    @BindView(R.id.ivBirthDate)
    ImageView ivBirthDate;
    @BindView(R.id.ivPassAgain)
    ImageView ivPassAgain;
    @BindView(R.id.ivPass)
    ImageView ivPass;
    @BindView(R.id.tvBirthDate)
    TextView tvBirthDate;
    @BindView(R.id.rbMale)
    RadioButton rbMale;
    @BindView(R.id.rbFemale)
    RadioButton rbFemale;
    @BindView(R.id.etDay)
    EditText etDay;
    @BindView(R.id.etMonth)
    EditText etMonth;
    @BindView(R.id.etYear)
    EditText etYear;

    UsuarioPresenter usuarioPresenter;
    CountryPresenter countryPresenter;
    GenderPresenter genderPresenter;
    ArrayList<Country> countries;
    ArrayList<Gender> genders;
    String birthDay;
    boolean pickewIsViewing;
    boolean passView = false, passAgainView = false;
    SingleDateAndTimePickerDialog datePicker;
    TransparentProgressDialog loading;
    final Calendar myCalendar = Calendar.getInstance();
    SingleClick singleClick;
    SingleDateAndTimePicker singleDateAndTimePicker;

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user_activity);
        injectView();
        initUI();
        loadPresenter();
        textChangeEvents();
        maxLenghs();
    }

    void initUI() {
        pickewIsViewing = false;
        birthDay = "";
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        loading = new TransparentProgressDialog(getContext());
        onClickListener();
        ivContinue.setOnClickListener(singleClick);
        ivClose.setOnClickListener(singleClick);
        ivPass.setOnClickListener(singleClick);
        ivPassAgain.setOnClickListener(singleClick);

    }

    private void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {
                switch (v.getId()) {
                    case R.id.ivContinue:
                        clickContinue();
                        break;
                    case R.id.ivClose:
                        finish();
                        break;
                    case R.id.ivBirthDate:
                        if (pickewIsViewing) {
                        } else {
                            loadDateIOS();
                        }
                        break;
                    case R.id.ivPass:
                        if (!passView) {
                            etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passView = true;
                        } else {
                            etPass.setTransformationMethod(null);
                            passView = false;
                        }
                        break;
                    case R.id.ivPassAgain:
                        if (!passAgainView) {
                            etPassAgain.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passAgainView = true;
                        } else {
                            etPassAgain.setTransformationMethod(null);
                            passAgainView = false;
                        }
                        break;
                }
            }
        };
    }


    void clickContinue() {
        String day = etDay.getText().toString();
        String month = etMonth.getText().toString();
        String year = etYear.getText().toString();
        birthDay = day + "/" + month + "/" + year;
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        String name = etNames.getText().toString();
        String idTemporal = Helper.getUserAppPreference(getContext()).getIdTemporal();
        String country = "";
        String sex = "";

        String passEnconded = "";
        try {
            passEnconded = Helper.hash256(pass);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

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

        if (day.equals("")) {
            day = "0";
        }

        if (month.equals("")) {
            month = "0";
        }

        if (year.equals("")) {
            year = "0";
        }

        if (Helper.isEmailValid(email)) {

            if (etPass.getText().length() > 5) {

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
                            if (birthDay.length() < 10) {
                                birthDay = "";
                            }
                            if (!loading.isShowing()) {
                                loading.show();
                            }

                            SharedPreferences preferenciasssee = getContext().getSharedPreferences("Preference_Pass", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editoriieei = preferenciasssee.edit();
                            editoriieei.putString("Pass", pass);
                            editoriieei.apply();
                            usuarioPresenter.registerUser(Helper.getUserAppPreference(getContext()).getToken(), name, birthDay, sex, country, email, passEnconded, idTemporal, Constants.REGISTER_TYPES.EMAIL, Constants.SYSTEM.APP);
                        }
                    }
                }


            } else {

                Toast.makeText(getApplicationContext(), "Completa los datos correctamente", Toast.LENGTH_SHORT).show();

            }

        } else {
            Toast.makeText(getApplicationContext(), "Completa los datos correctamente", Toast.LENGTH_SHORT).show();
        }
    }


    public void setSpinner(ArrayList<Country> mCountries, Spinner spiner, Context ctx) {
        final List<String> paises = new ArrayList<String>();// = new ArrayList<>(Arrays.asList(RubroNegocio_array));
        for (Integer i = 0; i < mCountries.size(); i++) {
            paises.add(mCountries.get(i).getNameParameterValue());
        }
        final ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                ctx, R.layout.spinneritem, paises) {
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
            }
        });
        textchangeListener();
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


    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        String dateComplete = sdf.format(myCalendar.getTime());
        String day = dateComplete.substring(0, 2);
        String month = dateComplete.substring(3, 5);
        String year = dateComplete.substring(6, 10);
        etDay.setText(day);
        etMonth.setText(month);
        etYear.setText(year);
        tvBirthDate.setText(dateComplete);
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


    void loadDateIOS() {
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
                        String dateComplete = sdf.format(date);
                        birthDay = dateComplete;
                        String day = dateComplete.substring(0, 2);
                        String month = dateComplete.substring(3, 5);
                        String year = dateComplete.substring(6, 10);
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
        UserPreference userPreference = Helper.getUserAppPreference(getContext());
        userPreference.setEmail(usuario.getEmail());
        Helper.saveUserAppPreference(getContext(), userPreference);
        next(ValidationCodeActivity.class, null);
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
    public void versionApp(String version) {
    }

    @Override
    public void imageUploaded(String message) {
    }

    @Override
    public void countryListLoaded(List<Country> mCountries) {
        countries = (ArrayList<Country>) mCountries;
        setSpinner(countries, spiPaises, getApplicationContext());
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
        if (loading.isShowing()) {
            loading.dismiss();
        }
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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