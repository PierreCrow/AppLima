package com.avances.lima.presentation.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

public class BaseFragment extends Fragment {

    protected void injectView(View v) {
        setHasOptionsMenu(true);
        ButterKnife.bind(this, v);
    }
    protected void next(Class<?> activity, Context context, Bundle bundle) {
        Intent intent = new Intent(context, activity);
        if(bundle!=null)
        { intent.putExtra("extra",bundle);}
        startActivity(intent);
    }
}
