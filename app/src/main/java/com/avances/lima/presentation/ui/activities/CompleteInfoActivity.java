package com.avances.lima.presentation.ui.activities;

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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

public class CompleteInfoActivity extends BaseActivity
        implements UsuarioView, CountryView, GenderView {

    @BindView(R.id.ivContinue)
    ImageView ivContinue;
    @BindView(R.id.spiPaises)
    Spinner spiPaises;
    @BindView(R.id.etEmail)
    TextInputEditText etEmail;
    @BindView(R.id.etNames)
    TextInputEditText etNames;
    @BindView(R.id.tiEmail)
    TextInputLayout tiEmail;
    @BindView(R.id.tiNames)
    TextInputLayout tiNames;
    @BindView(R.id.ivBirthDate)
    ImageView ivBirthDate;
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

    CountryPresenter countryPresenter;
    GenderPresenter genderPresenter;
    ArrayList<Country> countries;
    ArrayList<Gender> genders;
    TransparentProgressDialog loading;
    String birthDay;
    UsuarioPresenter usuarioPresenter;
    SingleClick singleClick;
    final Calendar myCalendar = Calendar.getInstance();

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
        setContentView(R.layout.complete_info_activity);
        injectView();
        initUI();
        loadPresenter();
        textChangeEvents();
        maxLenghs();
        setFields();
    }

    void initUI() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        onClickListener();
        loading = new TransparentProgressDialog(getContext());
        ivContinue.setOnClickListener(singleClick);
    }

    private void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {

                switch (v.getId()) {
                    case R.id.ivContinue:
                        clickContinue();
                        break;
                }
            }
        };
    }


    void clickContinue() {
        String email = etEmail.getText().toString();
        String name = etNames.getText().toString();
        birthDay = tvBirthDate.getText().toString();
        String country = "";
        String sex = "";
        String idTemporal = Helper.getUserAppPreference(getContext()).getIdTemporal();

        String day = etDay.getText().toString();
        String month = etMonth.getText().toString();
        String year = etYear.getText().toString();

        birthDay = day + "/" + month + "/" + year;
        String countrySelected = spiPaises.getSelectedItem().toString();
        for (Country pais : countries) {
            if (countrySelected.equals(pais.getNameParameterValue())) {
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

            if (Integer.parseInt(day) > 31) {
                birthDay = "";
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.complete_info_validDay), Toast.LENGTH_SHORT).show();
                etDay.setError(getResources().getString(R.string.complete_info_DayError));

            } else {
                if (Integer.parseInt(month) > 12) {
                    birthDay = "";
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.complete_info_validMonth), Toast.LENGTH_SHORT).show();
                    etMonth.setError(getResources().getString(R.string.complete_info_MonthError));
                } else {
                    if (Integer.parseInt(year) > 2020) {
                        birthDay = "";
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.complete_info_validYear), Toast.LENGTH_SHORT).show();
                        etMonth.setError(getResources().getString(R.string.complete_info_YearError));
                    } else {
                        if (birthDay.length() < 10) {
                            birthDay = "";
                        }
                        if (!loading.isShowing()) {
                            loading.show();
                        }
                        usuarioPresenter.updateUser(Helper.getUserAppPreference(getContext()).getToken(), name, birthDay, sex, country, email, Helper.getUserAppPreference(getContext()).getPass(), Helper.getUserAppPreference(getContext()).getRegisterLoginType(), Constants.SYSTEM.APP);
                    }
                }
            }

        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.complete_info_validInfo), Toast.LENGTH_SHORT).show();
        }
    }


    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tvBirthDate.setText(sdf.format(myCalendar.getTime()));
    }


    public void setSpinner(ArrayList<Country> countries, Spinner spiner, Context ctx) {
        final List<String> paises = new ArrayList<String>();

        for (Integer i = 0; i < countries.size(); i++) {
            paises.add(countries.get(i).getNameParameterValue());
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


    void setFields() {
        UserPreference userPreference = Helper.getUserAppPreference(getContext());
        etEmail.setText(userPreference.getEmail());
        etNames.setText(userPreference.getName());
        if (!userPreference.getCountry().equals("")) {
            for (int i = 0; i < countries.size(); i++) {
                if (userPreference.getCountry().equals(countries.get(i).getId())) {
                    spiPaises.setSelection(i);
                }
            }
        }
        if (!userPreference.getGender().equals("")) {
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
                        tiEmail.setError(getResources().getString(R.string.complete_info_EmailError));
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

    void maxLenghs() {
        int lenghtEmail = 50;
        etEmail.setFilters(new InputFilter[]{new InputFilter.LengthFilter(lenghtEmail)});
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
        next(MainActivity.class, null);
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
    }

    @Override
    public Context getContext() {
        return this;
    }

}