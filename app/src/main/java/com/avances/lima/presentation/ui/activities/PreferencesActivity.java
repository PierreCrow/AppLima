package com.avances.lima.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.avances.lima.R;
import com.avances.lima.domain.model.Interest;
import com.avances.lima.domain.model.Permanency;
import com.avances.lima.domain.model.UserPreference;
import com.avances.lima.domain.model.Usuario;
import com.avances.lima.presentation.presenter.InterestPresenter;
import com.avances.lima.presentation.presenter.UsuarioPresenter;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.utils.SingleClick;
import com.avances.lima.presentation.utils.TinyDB;
import com.avances.lima.presentation.view.InterestView;
import com.avances.lima.presentation.view.UsuarioView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PreferencesActivity extends BaseActivity
        implements InterestView, UsuarioView {


    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.ivContinue)
    ImageView ivContinue;
    @BindView(R.id.btnInteres1)
    TextView btnInteres1;
    @BindView(R.id.btnInteres2)
    TextView btnInteres2;
    @BindView(R.id.btnInteres3)
    TextView btnInteres3;
    @BindView(R.id.btnInteres4)
    TextView btnInteres4;
    @BindView(R.id.btnInteres5)
    TextView btnInteres5;
    @BindView(R.id.btnInteres6)
    TextView btnInteres6;
    @BindView(R.id.rlInteres1)
    RelativeLayout rlInteres1;
    @BindView(R.id.rlInteres2)
    RelativeLayout rlInteres2;
    @BindView(R.id.rlInteres3)
    RelativeLayout rlInteres3;
    @BindView(R.id.rlInteres4)
    RelativeLayout rlInteres4;
    @BindView(R.id.rlInteres5)
    RelativeLayout rlInteres5;
    @BindView(R.id.rlInteres6)
    RelativeLayout rlInteres6;
    @BindView(R.id.spiPermanency)
    Spinner spiPermanency;

    InterestPresenter interestPresenter;
    List<Interest> interests;
    boolean interes1Pressed, interes2Pressed, interes3Pressed, interes4Pressed, interes5Pressed, interes6Pressed;
    UsuarioPresenter usuarioPresenter;
    ArrayList<Permanency> permanencies;
    UserPreference userPreference;
    SingleClick singleClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.preferences_activity);

        injectView();
        initUI();
        loadPresenter();
        setInterests();
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
                        loadLoginActivity();
                        break;
                    case R.id.btnInteres1:
                        if (interes1Pressed) {
                            btnInteres1.setTextColor(Color.BLACK);
                            rlInteres1.setBackgroundResource(R.drawable.shape_home_filter_interes_off);
                            interes1Pressed = false;
                        } else {
                            btnInteres1.setTextColor(Color.WHITE);
                            rlInteres1.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                            interes1Pressed = true;
                        }
                        break;
                    case R.id.btnInteres2:
                        if (interes2Pressed) {
                            btnInteres2.setTextColor(Color.BLACK);
                            rlInteres2.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                            interes2Pressed = false;
                        } else {
                            btnInteres2.setTextColor(Color.WHITE);
                            rlInteres2.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                            interes2Pressed = true;
                        }
                        break;
                    case R.id.btnInteres3:
                        if (interes3Pressed) {
                            btnInteres3.setTextColor(Color.BLACK);
                            rlInteres3.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                            interes3Pressed = false;
                        } else {
                            btnInteres3.setTextColor(Color.WHITE);
                            rlInteres3.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                            interes3Pressed = true;
                        }
                        break;
                    case R.id.btnInteres4:
                        if (interes4Pressed) {
                            btnInteres4.setTextColor(Color.BLACK);
                            rlInteres4.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                            interes4Pressed = false;
                        } else {
                            btnInteres4.setTextColor(Color.WHITE);
                            rlInteres4.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                            interes4Pressed = true;
                        }
                        break;
                    case R.id.btnInteres5:
                        if (interes5Pressed) {
                            btnInteres5.setTextColor(Color.BLACK);
                            rlInteres5.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                            interes5Pressed = false;
                        } else {
                            btnInteres5.setTextColor(Color.WHITE);
                            rlInteres5.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                            interes5Pressed = true;
                        }
                        break;
                }
            }
        };
    }


    private void initUI() {

        userPreference = Helper.getUserAppPreference(getContext());

        onClickListener();
        ivClose.setOnClickListener(singleClick);
        ivContinue.setOnClickListener(singleClick);
        btnInteres1.setOnClickListener(singleClick);
        btnInteres2.setOnClickListener(singleClick);
        btnInteres3.setOnClickListener(singleClick);
        btnInteres4.setOnClickListener(singleClick);
        btnInteres5.setOnClickListener(singleClick);


        interes1Pressed = false;
        interes2Pressed = false;
        interes3Pressed = false;
        interes4Pressed = false;
        interes5Pressed = false;

        permanencies = new ArrayList<>();

        Permanency permanency1 = new Permanency("DIPE0001", "1 día", "", true);
        Permanency permanency2 = new Permanency("DIPE0002", "2 días", "", true);
        Permanency permanency3 = new Permanency("DIPE0003", "+ 3 días", "", true);
        Permanency permanency4 = new Permanency("DIPE0004", "No estoy seguro", "", true);

        permanencies.add(permanency1);
        permanencies.add(permanency2);
        permanencies.add(permanency3);
        permanencies.add(permanency4);

        setSpinner(permanencies, spiPermanency, getContext());
    }

    void loadPresenter() {
        interestPresenter = new InterestPresenter();
        interestPresenter.addView(this);
        interestPresenter.getInterests(Constants.STORE.DB);

        usuarioPresenter = new UsuarioPresenter();
        usuarioPresenter.addView(this);
    }

    @Override
    public void interestListLoaded(List<Interest> mInterests) {
        interests = mInterests;

        btnInteres1.setText(interests.get(0).getDetailParameterValue());
        btnInteres2.setText(interests.get(1).getDetailParameterValue());
        btnInteres3.setText(interests.get(2).getDetailParameterValue());
        btnInteres4.setText(interests.get(3).getDetailParameterValue());
        btnInteres5.setText(interests.get(4).getDetailParameterValue());
        btnInteres6.setText(interests.get(5).getDetailParameterValue());

    }

    @Override
    public void interestCreated(String message) {

    }

    @Override
    public void interestUpdated(String message) {

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

        ArrayList<String> misIdRoutes = new ArrayList<>();

        for (String id : idRoutes) {
            misIdRoutes.add(id);
        }

        TinyDB tinydb = new TinyDB(getContext());
        tinydb.putListString("routesByInterests", misIdRoutes);

        //obtener rutas para luego pintarlas
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
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


    @Override
    public void onPause() {
        super.onPause();
    }


    void setInterests() {

        if (!userPreference.getInterest_1().equals("")) {
            btnInteres1.setTextColor(Color.WHITE);
            rlInteres1.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
            interes1Pressed = true;
        }

        if (!userPreference.getInterest_2().equals("")) {
            btnInteres2.setTextColor(Color.WHITE);
            rlInteres2.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
            interes1Pressed = true;
        }

        if (!userPreference.getInterest_3().equals("")) {
            btnInteres3.setTextColor(Color.WHITE);
            rlInteres3.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
            interes1Pressed = true;
        }

        if (!userPreference.getInterest_4().equals("")) {
            btnInteres4.setTextColor(Color.WHITE);
            rlInteres4.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
            interes1Pressed = true;
        }

        if (!userPreference.getInterest_5().equals("")) {
            btnInteres5.setTextColor(Color.WHITE);
            rlInteres5.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
            interes1Pressed = true;
        }


        for (int i = 0; i < permanencies.size(); i++) {
            if (userPreference.getPermanencyDays().equals(permanencies.get(i).getId())) {
                spiPermanency.setSelection(i + 1);
            }
        }


    }


    public void setSpinner(ArrayList<Permanency> permanencies, Spinner spiner, Context ctx) {
        final List<String> afectaciones = new ArrayList<String>();// = new ArrayList<>(Arrays.asList(RubroNegocio_array));
        afectaciones.add("Seleccionar día");


        for (Integer i = 0; i < permanencies.size(); i++) {
            afectaciones.add(permanencies.get(i).getNameParameterValue());
        }

        final ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                ctx, R.layout.spinneritem_seconds, afectaciones) {
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

        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinneritem_seconds);
        spiner.setAdapter(spinnerArrayAdapter1);
    }


    void loadLoginActivity() {

        List<String> misINtereses = new ArrayList<>();

        String permanencyDayName = spiPermanency.getSelectedItem().toString();
        String permanencyDaysId = "";

        UserPreference userPreference = Helper.getUserAppPreference(getContext());

        if (interes1Pressed) {
            userPreference.setInterest_1(interests.get(0).getId());
            misINtereses.add(interests.get(0).getId());
        }

        if (interes2Pressed) {
            userPreference.setInterest_2(interests.get(1).getId());
            misINtereses.add(interests.get(1).getId());
        }

        if (interes3Pressed) {
            userPreference.setInterest_3(interests.get(2).getId());
            misINtereses.add(interests.get(2).getId());
        }

        if (interes4Pressed) {
            userPreference.setInterest_4(interests.get(3).getId());
            misINtereses.add(interests.get(3).getId());
        }

        if (interes5Pressed) {
            userPreference.setInterest_5(interests.get(4).getId());
            misINtereses.add(interests.get(4).getId());
        }

/*
        if (interes6Pressed) {
            userPreference.setInterest_5(interests.get(5).getId());
            misINtereses.add(interests.get(5).getId());
        }
*/

        Helper.saveUserAppPreference(getContext(), userPreference);


        for (int i = 0; i < permanencies.size(); i++) {
            if (permanencyDayName.equals(permanencies.get(i).getNameParameterValue())) {
                permanencyDaysId = permanencies.get(i).getId();
            }
        }


        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);

        usuarioPresenter.routesByInterest(Helper.getUserAppPreference(getContext()).getToken(), misINtereses, permanencyDaysId);

    }

}