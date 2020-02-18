package com.avances.applima.presentation.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.avances.applima.R;
import com.avances.applima.domain.model.Country;
import com.avances.applima.domain.model.Interest;
import com.avances.applima.domain.model.Permanency;
import com.avances.applima.domain.model.UserPreference;
import com.avances.applima.domain.model.Usuario;
import com.avances.applima.presentation.presenter.InterestPresenter;
import com.avances.applima.presentation.presenter.UsuarioPresenter;
import com.avances.applima.presentation.ui.activities.LoginActivity;
import com.avances.applima.presentation.ui.activities.MainActivity;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.utils.TinyDB;
import com.avances.applima.presentation.view.InterestView;
import com.avances.applima.presentation.view.UsuarioView;

import java.util.ArrayList;
import java.util.List;

public class SecondsToOfferFragment extends BaseFragment implements InterestView, UsuarioView {


    ImageView ivClose, ivContinue;
    InterestPresenter interestPresenter;
    List<Interest> interests;
    TextView btnInteres1, btnInteres2, btnInteres3, btnInteres4, btnInteres5,btnInteres6;
    RelativeLayout rlInteres1, rlInteres2, rlInteres3, rlInteres4, rlInteres5,rlInteres6;
    boolean interes1Pressed, interes2Pressed, interes3Pressed, interes4Pressed, interes5Pressed,interes6Pressed;
    Spinner spiPermanency;
    UsuarioPresenter usuarioPresenter;
    ArrayList<Permanency> permanencies;


    void loadPresenter() {
        interestPresenter = new InterestPresenter();
        interestPresenter.addView(this);
        interestPresenter.getInterests(Constants.STORE.DB);

        usuarioPresenter= new UsuarioPresenter();
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

        ArrayList<String> misIdRoutes= new ArrayList<>();

        for(String id:idRoutes)
        {
            misIdRoutes.add(id);
        }

        TinyDB tinydb = new TinyDB(getContext());
        tinydb.putListString("routesByInterests", misIdRoutes);

        //obtener rutas para luego pintarlas
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
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


    public interface CierraDialogSeconds {
        public void onClose_Seconds(Boolean close);
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View x = inflater.inflate(R.layout.second_to_offer_modal, null);

        initUI(x);

        loadPresenter();

        clickEvents();

        showingValidation();

        return x;

    }

    void showingValidation() {


        UserPreference userPreference = Helper.getUserAppPreference(getContext());
        userPreference.setSecondsToOfferViewed(true);
        userPreference.setLogged(false);
        Helper.saveUserAppPreference(getContext(), userPreference);


    }

    private void initUI(View v) {
        ivClose = (ImageView) v.findViewById(R.id.ivClose);
        ivContinue = (ImageView) v.findViewById(R.id.ivContinue);
        btnInteres1 = (TextView) v.findViewById(R.id.btnInteres1);
        btnInteres2 = (TextView) v.findViewById(R.id.btnInteres2);
        btnInteres3 = (TextView) v.findViewById(R.id.btnInteres3);
        btnInteres4 = (TextView) v.findViewById(R.id.btnInteres4);
        btnInteres5 = (TextView) v.findViewById(R.id.btnInteres5);
     //   btnInteres6 = (TextView) v.findViewById(R.id.btnInteres6);

        rlInteres1=(RelativeLayout)v.findViewById(R.id.rlInteres1);
        rlInteres2=(RelativeLayout)v.findViewById(R.id.rlInteres2);
        rlInteres3=(RelativeLayout)v.findViewById(R.id.rlInteres3);
        rlInteres4=(RelativeLayout)v.findViewById(R.id.rlInteres4);
        rlInteres5=(RelativeLayout)v.findViewById(R.id.rlInteres5);
    //    rlInteres6=(RelativeLayout)v.findViewById(R.id.rlInteres6);

        spiPermanency=(Spinner) v.findViewById(R.id.spiPermanency);



        interes1Pressed = false;
        interes2Pressed = false;
        interes3Pressed = false;
        interes4Pressed = false;
        interes5Pressed = false;
     //   interes6Pressed = false;


        permanencies= new ArrayList<>();

        Permanency permanency1= new Permanency("DIPE0001","1 día","",true);
        Permanency permanency2= new Permanency("DIPE0002","2 días","",true);
        Permanency permanency3= new Permanency("DIPE0003","+ 3 días","",true);
        Permanency permanency4= new Permanency("DIPE0004","No estoy seguro","",true);

        permanencies.add(permanency1);
        permanencies.add(permanency2);
        permanencies.add(permanency3);
        permanencies.add(permanency4);

        SeteaSpinner(permanencies,spiPermanency,getContext());


    }

    void clickEvents() {

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  loadHomeFragment();
                //  MainActivity.showTabBarMenu();

                loadHomeFragment();


                //  sendCallbackImperdibles();

            }
        });


        ivContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadLoginActivity();

            }
        });


        btnInteres1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (interes1Pressed) {
                    btnInteres1.setTextColor(Color.BLACK);
                    rlInteres1.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                    interes1Pressed = false;
                } else {
                    btnInteres1.setTextColor(Color.WHITE);
                    rlInteres1.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    interes1Pressed = true;
                }


            }
        });
        btnInteres2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (interes2Pressed) {
                    btnInteres2.setTextColor(Color.BLACK);
                    rlInteres2.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                    interes2Pressed = false;
                } else {
                    btnInteres2.setTextColor(Color.WHITE);
                    rlInteres2.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    interes2Pressed = true;
                }
            }
        });
        btnInteres3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interes3Pressed) {
                    btnInteres3.setTextColor(Color.BLACK);
                    rlInteres3.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                    interes3Pressed = false;
                } else {
                    btnInteres3.setTextColor(Color.WHITE);
                    rlInteres3.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    interes3Pressed = true;
                }
            }
        });
        btnInteres4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interes4Pressed) {
                    btnInteres4.setTextColor(Color.BLACK);
                    rlInteres4.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                    interes4Pressed = false;
                } else {
                    btnInteres4.setTextColor(Color.WHITE);
                    rlInteres4.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    interes4Pressed = true;
                }
            }
        });
        btnInteres5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interes5Pressed) {
                    btnInteres5.setTextColor(Color.BLACK);
                    rlInteres5.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                    interes5Pressed = false;
                } else {
                    btnInteres5.setTextColor(Color.WHITE);
                    rlInteres5.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    interes5Pressed = true;
                }
            }
        });
/*
        btnInteres6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interes5Pressed) {
                    btnInteres6.setTextColor(Color.BLACK);
                    rlInteres6.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                    interes6Pressed = false;
                } else {
                    btnInteres6.setTextColor(Color.WHITE);
                    rlInteres6.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    interes6Pressed = true;
                }
            }
        });
*/
    }


    public void SeteaSpinner(ArrayList<Permanency> mis_afectas, Spinner spiner, Context ctx) {
        final List<String> afectaciones = new ArrayList<String>();// = new ArrayList<>(Arrays.asList(RubroNegocio_array));
        afectaciones.add("Seleccionar día");


        for (Integer i = 0; i < mis_afectas.size(); i++) {
            //  String temp=mis_afectas.get(i).getDetailParameterValue();
            //  String nickname = temp.substring(0, temp.indexOf(' '));
            afectaciones.add(mis_afectas.get(i).getNameParameterValue());
        }

        // Initializing an ArrayAdapter
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



    void loadHomeFragment() {


        if(Helper.getUserAppPreference(getContext()).isSecondsToOfferViewed())
        {
            next(MainActivity.class,getContext(),null);
        }
        else
        {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            // fragmentTransaction.setCustomAnimations(R.anim.slide_up_down, R.anim.slide_bottom);
            TabHome accountFragment = new TabHome();
            fragmentTransaction.replace(R.id.containerView, accountFragment);
            fragmentTransaction.commit();
        }


    }

    void loadLoginActivity() {

        List<String> misINtereses= new ArrayList<>();

        String permanencyDayName=spiPermanency.getSelectedItem().toString();
        String permanencyDaysId="";

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


        for(int i=0;i<permanencies.size();i++)
        {
            if(permanencyDayName.equals(permanencies.get(i).getNameParameterValue()))
            {
                permanencyDaysId=permanencies.get(i).getId();
            }
        }





        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);

        usuarioPresenter.routesByInterest(Helper.getUserAppPreference(getContext()).getToken(),misINtereses,permanencyDaysId);



    }

    void sendCallback() {
        Activity ahhh = getActivity();
        //   GastosFragment dda = (GastosFragment)getFragmentManager().findFragmentById(R.id.containerView);
        //  List<Fragment> ah=dda.getChildFragmentManager().getFragments();
        //  Fragment ahhh=ah.get(0);

        if (ahhh instanceof CierraDialogSeconds) {
            ((CierraDialogSeconds) ahhh).onClose_Seconds(true);
        }

    }

}