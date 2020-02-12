package com.avances.applima.presentation.ui.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.avances.applima.R;
import com.avances.applima.presentation.ui.activities.MainActivity;
import com.chaos.view.PinView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ValidationFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.ivContinue)
    ImageView ivContinue;
    TextView tvTittle,tvSubTittle1,tvSubTitle2,tvQuestion,tvContinue;
    EditText et1,et2,et3,et4,et5;
    LinearLayout llEditTextContainer,llInfoContainer;

    PinView pinView;




    @Override
    public void onPause() {
        super.onPause();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View x = inflater.inflate(R.layout.validation_activity, null);

      //  injectView(x);

        initUI(x);

        clickEvents();

        checkTabletMode();

        return x;

    }


    void checkTabletMode()
    {
        if(isTablet(getContext()))
        {
            setSizes();
        }
    }


    void setSizes()
    {
        tvTittle.setTextSize(TypedValue.COMPLEX_UNIT_SP,40);
        tvSubTittle1.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
        tvSubTitle2.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
        tvQuestion.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);

        tvContinue.setTextSize(TypedValue.COMPLEX_UNIT_SP,22);

        setEditTextTabletMode(et1);
        setEditTextTabletMode(et2);
        setEditTextTabletMode(et3);
        setEditTextTabletMode(et4);
        setEditTextTabletMode(et5);

        setMargins();

        setPinViewTabletMode();
    }


    void setMargins()
    {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10, 200, 25, 15);
        llEditTextContainer.setLayoutParams(params);


        RelativeLayout.LayoutParams paramsaa = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        paramsaa.setMargins(80, 0, 30, 0);
        llInfoContainer.setLayoutParams(paramsaa);


        RelativeLayout.LayoutParams paramsss = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        paramsss.setMargins(70, 70, 0, 0);
        ivClose.setLayoutParams(paramsss);


        RelativeLayout.LayoutParams paramssstt = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        paramssstt.setMargins(100, 0, 100, 0);
        paramssstt.addRule(RelativeLayout.CENTER_IN_PARENT);
        ivContinue.setLayoutParams(paramssstt);


        setButtonContinueTabletMode(ivContinue);

        setImageViewCloseTabletMode(ivClose);

    }

    void setEditTextTabletMode(EditText et)
    {
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) et.getLayoutParams();
        params.height = getResources().getDimensionPixelSize(R.dimen.edit_text_validation);
        params.width = getResources().getDimensionPixelSize(R.dimen.edit_text_validation);
        et.setLayoutParams(params);
    }


    void setButtonContinueTabletMode(ImageView et)
    {
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) et.getLayoutParams();
        params.height = getResources().getDimensionPixelSize(R.dimen.iv_continue_validation);
       // params.width = getResources().getDimensionPixelSize(R.dimen.edit_text_validation);
        et.setLayoutParams(params);
    }


    void setImageViewCloseTabletMode(ImageView et)
    {
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) et.getLayoutParams();
        params.height = getResources().getDimensionPixelSize(R.dimen.iv_close_validation);
         params.width = getResources().getDimensionPixelSize(R.dimen.iv_close_validation);
        et.setLayoutParams(params);
    }




    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }


    void setPinViewTabletMode()
    {
        pinView.setItemRadius(10);
        pinView.setAnimationEnable(true);
        pinView.setHideLineWhenFilled(false);
        pinView.setItemWidth(80);
        pinView.setItemHeight(80);
        pinView.setTextSize(22);
    }

    private void initUI(View v) {


        pinView=(PinView)v.findViewById(R.id.firstPinView);


        //-----------------------------------------

        ivClose = (ImageView) v.findViewById(R.id.ivClose);
        ivContinue = (ImageView) v.findViewById(R.id.ivContinue);
        tvContinue=(TextView)v.findViewById(R.id.tvContinue);

        tvTittle = (TextView) v.findViewById(R.id.tvTittle);
        tvSubTittle1 = (TextView) v.findViewById(R.id.tvSubTittle1);
        tvSubTitle2 = (TextView) v.findViewById(R.id.tvSubTittle2);
        tvQuestion = (TextView) v.findViewById(R.id.tvQuestion);

        et1 = (EditText) v.findViewById(R.id.et1);
        et2 = (EditText) v.findViewById(R.id.et2);
        et3 = (EditText) v.findViewById(R.id.et3);
        et4 = (EditText) v.findViewById(R.id.et4);
        et5 = (EditText) v.findViewById(R.id.et5);

        llEditTextContainer=(LinearLayout)v.findViewById(R.id.llEditTextContainer);
        llInfoContainer=(LinearLayout)v.findViewById(R.id.llInfoContainer);

      //  ivContinue.setOnClickListener(this);

    }

    void clickEvents() {

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadHomeFragment();


            }
        });

    }


    void loadHomeFragment() {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_out_down);
        HomeFragment accountFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.ivContinue:
                //String hola="hola";
            break;

            case R.id.ivClose:
                //String holass="hola";
                break;
        }
    }
}