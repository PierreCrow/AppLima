package com.avances.lima.presentation.ui.dialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;

import com.avances.lima.R;
import com.avances.lima.domain.model.Route;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.view.RouteView;

import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

public class InfografiaDialog extends DialogFragment implements RouteView {

    ImageView ivClose;
    LinearLayout transparent_linear_filter;
    View view;
    Route route;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        view = getActivity().getLayoutInflater().inflate(R.layout.infografia_dialog, new LinearLayout(getActivity()), false);
        Bundle bundle = getArguments();
        route = (Route) bundle.getSerializable("route");
        ImageView ivInfografia = (ImageView) view.findViewById(R.id.ivInfografia);
        Helper.urlToImageView(route.getInfoghraphy(), ivInfografia, getContext());
        PhotoViewAttacher pAttacher;
        pAttacher = new PhotoViewAttacher(ivInfografia);
        pAttacher.update();

        ivClose = (ImageView) view.findViewById(R.id.ivClose);
        transparent_linear_filter = (LinearLayout) view.findViewById(R.id.transparent_linear_filter);

        transparent_linear_filter.setOnClickListener(new View.OnClickListener() {
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

        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setContentView(view);
        return builder;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
    }

    @Override
    public void routeListLoaded(List<Route> routes) {
    }

    @Override
    public void routeCreated(String message) {
    }

    @Override
    public void routeUpdated(String message) {
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
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
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
