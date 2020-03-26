package com.avances.lima.presentation.ui.dialogfragment;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.avances.lima.R;
import com.avances.lima.domain.model.Event;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EventDetailDialog extends DialogFragment {

    ImageView ivClose;
    public static Event event;
    TextView tvTittle, tvLongDescription, tvDate;
    RelativeLayout rlAgendar;
    LinearLayout llImageEvent;
    ImageView ivEventImage;

    @Override
    public void onDismiss(DialogInterface dialog) {
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.event_detail, new LinearLayout(getActivity()), false);
        initUI(view);
        clickEvents();
        setFields();
        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setContentView(view);
        return builder;
    }


    void setFields() {
        Locale spanish = new Locale("es", "ES");
        tvTittle.setText(event.getTittle());
        tvLongDescription.setText(event.getDescription());
        Helper.urlToImageView(event.getImage(), ivEventImage, getContext());
        //  Helper.urlToImageView(event.getImage(),llImageEvent,getContext());
        String dtStart = event.getStartDate();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            Date date = format.parse(dtStart);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
            String diaa = dateFormat.format(date);
            SimpleDateFormat dateFormatMes = new SimpleDateFormat("MMMM");
            String mess = Helper.capitalizeFirstLetter(dateFormatMes.format(date));
            tvDate.setText(diaa + " de " + mess);
        } catch (ParseException e) {
        }
    }

    void initUI(View view) {
        Bundle bundle = getArguments();
        event = (Event) bundle.getSerializable("event");
        ivClose = (ImageView) view.findViewById(R.id.ivClose);
        tvTittle = (TextView) view.findViewById(R.id.tvTittle);
        tvLongDescription = (TextView) view.findViewById(R.id.tvLongDescription);
        tvDate = (TextView) view.findViewById(R.id.tvDate);
        rlAgendar = (RelativeLayout) view.findViewById(R.id.rlAgendar);
        llImageEvent = (LinearLayout) view.findViewById(R.id.llImageEvent);
        ivEventImage = (ImageView) view.findViewById(R.id.ivEventImage);
    }

    public boolean hasCalendarPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED
        ) {
            return false;
        } else {
            return true;
        }
    }

    void clickEvents() {
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        rlAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasCalendarPermission()) {
                    String dtStart = event.getStartDate();
                    String dtFinal = event.getFinalDate();
                    String tittle = event.getTittle();
                    String desxcription = event.getDescription();
                    Helper.addCalendarEvent(getContext(), tittle, desxcription, dtStart, dtFinal);
                    Toast.makeText(getContext(), "Agregado a Calendario", Toast.LENGTH_SHORT).show();
                } else {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_CALENDAR,
                                    Manifest.permission.READ_CALENDAR},
                            Constants.REQUEST_CODES.REQUEST_CODE_CALENDAR);
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.WRAP_CONTENT;
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

}
