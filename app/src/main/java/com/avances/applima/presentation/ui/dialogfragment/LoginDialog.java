package com.avances.applima.presentation.ui.dialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;

import com.avances.applima.R;

public class LoginDialog extends DialogFragment {


    ImageView ivClose;

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.complete_info, new LinearLayout(getActivity()), false);

        // initUI(view);


        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);

        builder.setContentView(view);
        return builder;

    }


    void initUI(View view) {

        ivClose = (ImageView) view.findViewById(R.id.ivClose);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();

            }
        });


        String texto="lorjkdn fjfjnd c idjdu orjkdn fjfjnd compudnd djdhudhnu jdnudndu idjdu orjkdn fjfjnd compudnd  djdhudhnu jdnudndu idjdu jdhudhnu jdnudndu idjdu orjkdn fjfjnd compudnd djdhudhnu jdnudndu idjdu jdhudhnu jdnudndu idjdu orjkdn fjfjnd compudnd  djdhudhnu jdnudndu idjdu";

        WebView vieww = (WebView) view.findViewById(R.id.wbInformacion);
        //view.setBackgroundColor(Color.argb(255,255,246,227));
        vieww.setBackgroundColor(Color.TRANSPARENT);
        String text;
        text = "<html><body><p style=\"font-size:17px\"  align=\"justify\">";
  /*      text+= "Horario de atencion: " +
                "<br>" +
                "Lunes a Viernes de 9:00 am - 6:00 pm" +
                "<br>" +
                "Sabado 9:00 am - 2:00 pm" +
                "<br><br>" +
                "Telefonos: " +
                "<br>" +
                "+51924225809" +
                "<br>" +
                "+51964900669" +
                "<br>" +
                "+51992316225";
        text+= "</p></body></html>";*/
        text+=texto;
        vieww.loadData(text, "text/html", "utf-8");
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
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            dialog.getWindow().setLayout(width, height);

            //se lo asigno a mi dialogfragment

            //con esto hago que sea invicible
         //   dialog.getWindow().getAttributes().alpha = 1f;
          //  dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dismiss();
    }


}
