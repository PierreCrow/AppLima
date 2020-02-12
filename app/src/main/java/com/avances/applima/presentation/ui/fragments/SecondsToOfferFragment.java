package com.avances.applima.presentation.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.avances.applima.R;
import com.avances.applima.domain.model.Interest;
import com.avances.applima.domain.model.UserPreference;
import com.avances.applima.presentation.presenter.InterestPresenter;
import com.avances.applima.presentation.ui.activities.LoginActivity;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.view.InterestView;

import java.util.List;

public class SecondsToOfferFragment extends BaseFragment implements InterestView {


    ImageView ivClose, ivContinue;
    InterestPresenter interestPresenter;
    List<Interest> interests;
    Button btnInteres1, btnInteres2, btnInteres3, btnInteres4, btnInteres5;
    boolean interes1Pressed, interes2Pressed, interes3Pressed, interes4Pressed, interes5Pressed;


    void loadPresenter() {
        interestPresenter = new InterestPresenter();
        interestPresenter.addView(this);
        interestPresenter.getInterests(Constants.STORE.DB);
    }

    @Override
    public void interestListLoaded(List<Interest> mInterests) {
        interests = mInterests;

        btnInteres1.setText(interests.get(0).getDetailParameterValue());
        btnInteres2.setText(interests.get(1).getDetailParameterValue());
        btnInteres3.setText(interests.get(2).getDetailParameterValue());
        btnInteres4.setText(interests.get(3).getDetailParameterValue());
        btnInteres5.setText(interests.get(4).getDetailParameterValue());

    }

    @Override
    public void interestCreated(String message) {

    }

    @Override
    public void interestUpdated(String message) {

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
        btnInteres1 = (Button) v.findViewById(R.id.btnInteres1);
        btnInteres2 = (Button) v.findViewById(R.id.btnInteres2);
        btnInteres3 = (Button) v.findViewById(R.id.btnInteres3);
        btnInteres4 = (Button) v.findViewById(R.id.btnInteres4);
        btnInteres5 = (Button) v.findViewById(R.id.btnInteres5);


        interes1Pressed = false;
        interes2Pressed = false;
        interes3Pressed = false;
        interes4Pressed = false;
        interes5Pressed = false;


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
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.BLACK);
                    }
                    btnInteres1.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                    interes1Pressed = false;
                } else {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.WHITE);
                    }
                    btnInteres1.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    interes1Pressed = true;
                }


            }
        });
        btnInteres2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (interes2Pressed) {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.BLACK);
                    }
                    btnInteres2.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                    interes2Pressed = false;
                } else {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.WHITE);
                    }
                    btnInteres2.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    interes2Pressed = true;
                }
            }
        });
        btnInteres3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interes3Pressed) {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.BLACK);
                    }
                    btnInteres3.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                    interes3Pressed = false;
                } else {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.WHITE);
                    }
                    btnInteres3.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    interes3Pressed = true;
                }
            }
        });
        btnInteres4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interes4Pressed) {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.BLACK);
                    }
                    btnInteres4.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                    interes4Pressed = false;
                } else {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.WHITE);
                    }
                    btnInteres4.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    interes4Pressed = true;
                }
            }
        });
        btnInteres5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interes5Pressed) {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.BLACK);
                    }
                    btnInteres5.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                    interes5Pressed = false;
                } else {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.WHITE);
                    }
                    btnInteres5.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    interes5Pressed = true;
                }
            }
        });

    }


    void loadHomeFragment() {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.setCustomAnimations(R.anim.slide_up_down, R.anim.slide_bottom);
        TabHome accountFragment = new TabHome();
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();
    }

    void loadLoginActivity() {

        UserPreference userPreference = Helper.getUserAppPreference(getContext());

        if (interes1Pressed) {
            userPreference.setInterest_1(interests.get(0).getDetailParameterValue());
        }

        if (interes2Pressed) {
            userPreference.setInterest_2(interests.get(1).getDetailParameterValue());
        }

        if (interes3Pressed) {
            userPreference.setInterest_3(interests.get(2).getDetailParameterValue());
        }

        if (interes4Pressed) {
            userPreference.setInterest_4(interests.get(3).getDetailParameterValue());
        }

        if (interes5Pressed) {
            userPreference.setInterest_5(interests.get(4).getDetailParameterValue());
        }


        Helper.saveUserAppPreference(getContext(), userPreference);

        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
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