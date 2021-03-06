package com.avances.lima.presentation.ui.dialogfragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.DialogFragment;

import com.avances.lima.R;
import com.avances.lima.domain.model.UserPreference;
import com.avances.lima.domain.model.Usuario;
import com.avances.lima.presentation.presenter.UsuarioPresenter;
import com.avances.lima.presentation.ui.activities.MainActivity;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.utils.TransparentProgressDialog;
import com.avances.lima.presentation.view.UsuarioView;
import com.facebook.login.LoginManager;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class LogoutDialog extends DialogFragment implements UsuarioView {

    ImageView ivClose;
    RelativeLayout rlLogOut, rlNotNow;
    UsuarioPresenter usuarioPresenter;
    TransparentProgressDialog loading;

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.cerrar_sesion_dialog, new LinearLayout(getActivity()), false);
        initUI(view);
        clickEvents();
        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setContentView(view);
        return builder;
    }


    void initUI(View view) {
        loading = new TransparentProgressDialog(getContext());
        usuarioPresenter = new UsuarioPresenter();
        usuarioPresenter.addView(this);
        ivClose = (ImageView) view.findViewById(R.id.ivClose);
        rlLogOut = (RelativeLayout) view.findViewById(R.id.rlLogOut);
        rlNotNow = (RelativeLayout) view.findViewById(R.id.rlNotNow);
    }


    void clickEvents() {
        rlLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                userPreference.setLogged(false);
                Helper.saveUserAppPreference(getContext(), userPreference);
                cliearSharedPrefernce();
            }
        });

        rlNotNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().getAttributes().alpha = 1f;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dismiss();
    }


    void cliearSharedPrefernce() {

        UserPreference userPreference = Helper.getUserAppPreference(getContext());
        if (userPreference.getRegisterLoginType().equals(Constants.REGISTER_TYPES.FACEBOOK)) {
            LoginManager.getInstance().logOut();
        }
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(Constants.PREFERENCES.PREFERENCE_CURRENT_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        if (!loading.isShowing()) {
            loading.show();
        }
        usuarioPresenter.generateToken();
    }


    @Override
    public void temporalUserRegistered(String idTempUser) {
    }

    @Override
    public void tokenGenerated(String token) {
        if (loading.isShowing()) {
            loading.dismiss();
        }

        UserPreference userPreference = Helper.getUserAppPreference(getContext());
        userPreference.setToken(token);
        userPreference.setSecondsToOfferViewed(true);
        Helper.saveUserAppPreference(getContext(), userPreference);

        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
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
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showErrorMessage(String message) {
    }
}
