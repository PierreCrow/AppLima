package com.avances.applima.presentation.ui.activities;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.avances.applima.R;
import com.avances.applima.domain.model.UserPreference;
import com.avances.applima.domain.model.Usuario;
import com.avances.applima.presentation.presenter.UsuarioPresenter;
import com.avances.applima.presentation.ui.fragments.BaseFragment;
import com.avances.applima.presentation.ui.fragments.HomeFragment;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.view.UsuarioView;
import com.chaos.view.PinView;

import butterknife.BindView;

public class ValidationActivity extends BaseActivity implements View.OnClickListener, UsuarioView {


    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.ivContinue)
    ImageView ivContinue;
    TextView tvTittle,tvSubTittle1,tvSubTitle2,tvQuestion,tvContinue;
    EditText et1,et2,et3,et4,et5;
    LinearLayout llEditTextContainer,llInfoContainer;
    UsuarioPresenter usuarioPresenter;
    PinView entryEditText;
    String code="";



    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.validation_activity);


        injectView();

        loadPresenter();

        initUI();

        clickEvents();

        checkTabletMode();


    }

    void loadPresenter()
    {
        usuarioPresenter= new UsuarioPresenter();
        usuarioPresenter.addView(this);
    }



    void checkTabletMode()
    {
        if(isTablet(getApplicationContext()))
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

    private void initUI() {
        entryEditText= (PinView) findViewById(R.id.firstPinView);
        ivClose = (ImageView) findViewById(R.id.ivClose);
        ivContinue = (ImageView) findViewById(R.id.ivContinue);
    //    tvContinue=(TextView)findViewById(R.id.tvContinue);

        tvTittle = (TextView) findViewById(R.id.tvTittle);
        tvSubTittle1 = (TextView) findViewById(R.id.tvSubTittle1);
        tvSubTitle2 = (TextView) findViewById(R.id.tvSubTittle2);
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        et5 = (EditText) findViewById(R.id.et5);

        llEditTextContainer=(LinearLayout)findViewById(R.id.llEditTextContainer);
        llInfoContainer=(LinearLayout)findViewById(R.id.llInfoContainer);

      // ivContinue.setOnClickListener(this);


        entryEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                code=charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    void clickEvents() {

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             finish();

            }
        });

        ivContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserPreference userPreference= Helper.getUserAppPreference(getContext());
                usuarioPresenter.validateCode(Helper.getUserAppPreference(getContext()).getToken(),userPreference.getEmail(),code);

            }
        });

        tvQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuarioPresenter.reSendCode(Helper.getUserAppPreference(getContext()).getToken(),Constants.SYSTEM.APP,Constants.REGISTER_TYPES.EMAIL,Helper.getUserAppPreference(getContext()).getEmail());

            }
        });


    }



    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.ivContinue:
               //next(MainActivity.class,null);
            break;

            case R.id.ivClose:
                finish();
                break;
        }
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

        Toast toast=Toast. makeText(getApplicationContext(),"Código enviado", Toast. LENGTH_SHORT);
        toast. setMargin(50,50);
        toast. show();
    }

    @Override
    public void userGot(Usuario usuario) {


    }

    @Override
    public void validateCodeSuccess(Usuario usuario) {

        UserPreference userPreference = Helper.getUserAppPreference(getApplicationContext());
        userPreference.setLogged(true);
        Helper.saveUserAppPreference(getApplicationContext(), userPreference);

        Toast toast=Toast. makeText(getApplicationContext(),"Código correcto", Toast. LENGTH_SHORT);
        toast. setMargin(50,50);
        toast. show();

        next(MainActivity.class,null);


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

    @Override
    public Context getContext() {
        return this;
    }
}