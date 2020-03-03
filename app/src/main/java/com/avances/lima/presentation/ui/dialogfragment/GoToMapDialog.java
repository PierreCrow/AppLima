package com.avances.lima.presentation.ui.dialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.DialogFragment;

import com.avances.lima.R;

public class GoToMapDialog extends DialogFragment {


    ImageView ivClose;
    RelativeLayout  rlGoogleMaps,rlWaze;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.continuar_en_dialog, new LinearLayout(getActivity()), false);

        initUI(view);
        clickEvents();

        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);

        builder.setContentView(view);
        return builder;

    }


    void initUI(View view) {
        ivClose = (ImageView) view.findViewById(R.id.ivClose);
        rlGoogleMaps = (RelativeLayout) view.findViewById(R.id.rlGoogleMaps);
        rlWaze = (RelativeLayout) view.findViewById(R.id.rlWaze);
    }


    void clickEvents() {
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        rlGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGoogleMaps();
            }
        });
        rlWaze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWaze();
            }
        });
    }


    void goToGoogleMaps()
    {
        String lat="-12.072063";
        String lng="-77.015053";

        //navegacion
      //  String uri = "google.navigation:q=" + lat + "," + lng;

        Uri gmmIntentUri = Uri.parse("geo:"+lat+" ,"+lng);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(mapIntent);
        }


       // startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
    }

    void goToWaze()
    {

        String lat="-12.072063";
        String lng="-77.015053";

     /*   try {
            // Launch Waze
            String mapRequest = "https://waze.com/ul?q=" + lat + "," + lng + "&navigate=yes&zoom=17";
            Uri gmmIntentUri = Uri.parse(mapRequest);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.waze");
            startActivity(mapIntent);

        } catch (ActivityNotFoundException e) {
            // If Waze is not installed, open it in Google Play
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.waze"));
            startActivity(intent);
        }
*/

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-12.072063,-77.015053?q=-12.072063,-77.015053(Label+Nombreee)"));
        startActivity(intent);

    }


    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
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
