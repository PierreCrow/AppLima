package com.avances.lima.presentation.ui.activities;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avances.lima.R;
import com.avances.lima.domain.model.UserPreference;
import com.avances.lima.domain.model.Usuario;
import com.avances.lima.presentation.presenter.UsuarioPresenter;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.utils.SingleClick;
import com.avances.lima.presentation.utils.TransparentProgressDialog;
import com.avances.lima.presentation.view.UsuarioView;
import com.chaos.view.PinView;

import java.util.List;

import butterknife.BindView;

public class ValidationCodeActivity extends BaseActivity implements UsuarioView {

    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.ivContinue)
    ImageView ivContinue;
    @BindView(R.id.firstPinView)
    PinView entryEditText;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.tvSubTittle1)
    TextView tvSubTittle1;
    @BindView(R.id.tvSubTittle2)
    TextView tvSubTitle2;
    @BindView(R.id.tvQuestion)
    TextView tvQuestion;
    @BindView(R.id.tvContinue)
    TextView tvContinue;
    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.et3)
    EditText et3;
    @BindView(R.id.et4)
    EditText et4;
    @BindView(R.id.et5)
    EditText et5;
    @BindView(R.id.llEditTextContainer)
    LinearLayout llEditTextContainer;
    @BindView(R.id.llInfoContainer)
    LinearLayout llInfoContainer;

    UsuarioPresenter usuarioPresenter;
    String code = "";
    TransparentProgressDialog loading;
    SingleClick singleClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.validation_activity);
        injectView();
        loadPresenter();
        initUI();
        checkTabletMode();
    }

    private void initUI() {
        loading = new TransparentProgressDialog(getContext());

        onClickListener();
        ivClose.setOnClickListener(singleClick);
        ivContinue.setOnClickListener(singleClick);
        tvQuestion.setOnClickListener(singleClick);

        entryEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                code = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
    }


    private void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {

                switch (v.getId()) {
                    case R.id.ivContinue:
                        if (!loading.isShowing()) {
                            loading.show();
                        }
                        UserPreference userPreference = Helper.getUserAppPreference(getContext());
                        usuarioPresenter.validateCode(Helper.getUserAppPreference(getContext()).getToken(), userPreference.getEmail(), code);
                        break;

                    case R.id.ivClose:
                        finish();
                        break;
                    case R.id.tvQuestion:
                        if (!loading.isShowing()) {
                            loading.show();
                        }
                        usuarioPresenter.reSendCode(Helper.getUserAppPreference(getContext()).getToken(), Constants.SYSTEM.APP, Constants.REGISTER_TYPES.EMAIL, Helper.getUserAppPreference(getContext()).getEmail());
                        break;
                }
            }
        };
    }


    void loadPresenter() {
        usuarioPresenter = new UsuarioPresenter();
        usuarioPresenter.addView(this);
    }


    void checkTabletMode() {
        if (isTablet(getApplicationContext())) {
            setSizes();
        }
    }


    void setSizes() {
        tvTittle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
        tvSubTittle1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        tvSubTitle2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        tvQuestion.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        tvContinue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);

        setEditTextTabletMode(et1);
        setEditTextTabletMode(et2);
        setEditTextTabletMode(et3);
        setEditTextTabletMode(et4);
        setEditTextTabletMode(et5);

        setMargins();
    }


    void setMargins() {
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

    void setEditTextTabletMode(EditText et) {
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) et.getLayoutParams();
        params.height = getResources().getDimensionPixelSize(R.dimen.edit_text_validation);
        params.width = getResources().getDimensionPixelSize(R.dimen.edit_text_validation);
        et.setLayoutParams(params);
    }


    void setButtonContinueTabletMode(ImageView et) {
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) et.getLayoutParams();
        params.height = getResources().getDimensionPixelSize(R.dimen.iv_continue_validation);
        // params.width = getResources().getDimensionPixelSize(R.dimen.edit_text_validation);
        et.setLayoutParams(params);
    }


    void setImageViewCloseTabletMode(ImageView et) {
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

        if (loading.isShowing()) {
            loading.dismiss();
        }

        Toast.makeText(getApplicationContext(), "Código enviado", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void userGot(Usuario usuario) {


    }

    @Override
    public void validateCodeSuccess(Usuario usuario) {

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

        Toast.makeText(getApplicationContext(), "Código correcto", Toast.LENGTH_SHORT).show();

        next(MainActivity.class, null);
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