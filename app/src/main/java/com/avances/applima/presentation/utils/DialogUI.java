package com.avances.applima.presentation.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.avances.applima.R;

public class DialogUI {

    private Context context;


    public DialogUI(Context context) {
        this.context = context;
    }


    public void showDialogConfirmationNoCancelableTxtConfirm(String title, String question, String txtConfirm, int type, final ConfirmationDialogCallback callback) {
        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(false);
        this._showDialogConfirmationTxtConfirm(dialog, title, type, question, txtConfirm, callback);
    }


    private void _showDialogConfirmationTxtConfirm(final Dialog dialog, String title, int type, String question, String txtConfirm, final ConfirmationDialogCallback callback) {
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_confirm);
        TextView tvTitle = dialog.findViewById(R.id.title_alert_question);
        TextView tvQuestion = dialog.findViewById(R.id.txt_question);

        tvTitle.setText(title);
        tvQuestion.setText(question);

      //  ImageView imageView = dialog.findViewById(R.id.imageViewIcon);
      //  imageView.setImageResource(_getDrawableIconToModal(type));

        Button btnConfirm = dialog.findViewById(R.id.btn_accept_alerts);
        btnConfirm.setText(txtConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (callback != null) {
                    callback.onConfirmDialog();
                }
            }
        });

        dialog.show();
    }


}
