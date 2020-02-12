package com.avances.applima.presentation.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.avances.applima.R;
import com.avances.applima.presentation.utils.Helper;

public class PreferencesActivity extends BaseActivity {


    ImageView ivBack;

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.preferences_activity);

        initUI();
        clickEvents();
        Helper.hideKeyboard(PreferencesActivity.this);

      //  getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }


    void initUI()
    {
        ivBack = (ImageView) findViewById(R.id.ivBack);
    }


    void clickEvents()
    {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

}