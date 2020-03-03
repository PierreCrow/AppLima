package com.avances.lima.presentation.ui.dialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;

import com.avances.lima.R;

public class RateAppDialog extends DialogFragment {


    Button btnMiraflores, btnPuebloLibre, btnBarranco;
    ImageView ivClose;
    LinearLayout transparent_linear_filter;

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.rate_app_dialog, new LinearLayout(getActivity()), false);

        // initUI(view);

        ivClose = (ImageView) view.findViewById(R.id.ivClose);
      //  transparent_linear_filter = (LinearLayout) view.findViewById(R.id.transparent_linear_filter);



        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();

            }
        });

        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);

        builder.setContentView(view);
        return builder;

    }


    void initUI(View view) {
        //  btnMiraflores = (Button) view.findViewById(R.id.btnMirafloresFilter);
        //  btnPuebloLibre = (Button) view.findViewById(R.id.btnPuebloLibreFilter);
        //  btnBarranco = (Button) view.findViewById(R.id.btnBarrancoFilter);

    /*    int sizee=btnBarranco.getMeasuredHeight();

        btnBarranco.setLayoutParams(new LinearLayout.LayoutParams(sizee, sizee));
        btnPuebloLibre.setLayoutParams(new LinearLayout.LayoutParams(sizee, sizee));
        btnBarranco.setLayoutParams(new LinearLayout.LayoutParams(sizee, sizee));
        */

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
