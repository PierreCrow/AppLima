package com.avances.applima.presentation.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.avances.applima.R;
import com.avances.applima.domain.model.UserPreference;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;

public class EditProfileActivity extends BaseActivity {

    ImageView ivBack, ivUpdate;
    EditText etUserEmail, etUserPassword, etUserPhone;
    UserPreference userPreference;


    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_profile_activity);

        initUI();
        clickEvents();
        setFields();

    }

    void initUI() {

        userPreference = Helper.getUserAppPreference(getApplicationContext());

        etUserEmail = (EditText) findViewById(R.id.etUserEmail);
        etUserPassword = (EditText) findViewById(R.id.etUserPassword);
        etUserPhone = (EditText) findViewById(R.id.etUserPhone);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivUpdate = (ImageView) findViewById(R.id.ivUpdate);
    }


    void clickEvents() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        ivUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

    void setFields() {
        etUserEmail.setText(userPreference.getEmail());
        etUserPhone.setText(userPreference.getPhone());
        if (userPreference.getRegisterLoginType().equals( Constants.REGISTER_TYPES.EMAIL)) {
            etUserPassword.setText(userPreference.getPass());
        } else {
            etUserPassword.setEnabled(false);
        }
    }


}