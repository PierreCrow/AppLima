package com.avances.lima.presentation.ui.dialogfragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.avances.lima.R;
import com.avances.lima.presentation.ui.activities.MainActivity;
import com.avances.lima.presentation.ui.fragments.HomeFragment;
import com.avances.lima.presentation.utils.SingleClick;
import com.avances.lima.presentation.utils.TransparentProgressDialog;

public class NewSyncDialog extends DialogFragment {

    SingleClick singleClick;
    ImageView ivClose;
    RelativeLayout rlContinue;

    TransparentProgressDialog loading;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.update_database_dialog, new LinearLayout(getActivity()), false);

        initUI(view);

        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setContentView(view);
        return builder;
    }


    void initUI(View view) {
        ivClose = (ImageView) view.findViewById(R.id.ivClose);
        rlContinue = (RelativeLayout) view.findViewById(R.id.rlContinue);
        onClickListener();
        ivClose.setOnClickListener(singleClick);
        rlContinue.setOnClickListener(singleClick);
        loading= new TransparentProgressDialog(getContext());
    }


    void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {

                switch (v.getId()) {
                    case R.id.ivClose:
                       // dismiss();
                        break;
                    case R.id.rlContinue:
                      // dismiss();
                        timerSync(4);
                        break;
                }
            }
        };
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


    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    public interface CloseNewSync {
        public void onCloseNewSync();
    }

    void sendCallback() {
        Fragment ahhh=new HomeFragment();

        if (ahhh instanceof CloseNewSync) {
            ((CloseNewSync) ahhh).onCloseNewSync();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
     //   sendCallback();
    }

    void timerSync(Integer seconds) {

        long secondsMill = seconds * 10;

        if(!loading.isShowing())
        {
            loading.show();
        }

        new CountDownTimer(10000, secondsMill) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

                if (loading.isShowing()) {
                    loading.dismiss();
                }

                dismiss();
                Intent intent= new Intent(getContext(),MainActivity.class);
            }

        }.start();


    }

}
