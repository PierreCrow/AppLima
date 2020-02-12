package com.avances.applima.presentation.ui.dialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.DialogFragment;

import com.avances.applima.R;
import com.avances.applima.domain.model.UserPreference;
import com.avances.applima.presentation.ui.activities.MainActivity;
import com.avances.applima.presentation.utils.Helper;

public class CerrarSesionDialog extends DialogFragment {


    ImageView ivClose;
    RelativeLayout rlLogOut, rlNotNow;

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

        ivClose = (ImageView) view.findViewById(R.id.ivClose);
        rlLogOut = (RelativeLayout) view.findViewById(R.id.rlLogOut);
        rlNotNow = (RelativeLayout) view.findViewById(R.id.rlNotNow);
    }


    void clickEvents() {
        rlLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  Helper.logOut(getContext(), Helper.getUserAppPreference(getContext()).getRegisterLoginType());
                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                userPreference.setLogged(false);
                Helper.saveUserAppPreference(getContext(), userPreference);
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);

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

            //numero de pixeles que tendra de ancho
            // int width = 700;
            int width = ViewGroup.LayoutParams.MATCH_PARENT;

            //la altura se ajustara al contenido
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;

            dialog.getWindow().setLayout(width, height);

            //se lo asigno a mi dialogfragment

            //con esto hago que sea invicible
            dialog.getWindow().getAttributes().alpha = 1f;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dismiss();
    }


}
