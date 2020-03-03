package com.avances.lima.presentation.utils;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class ClaseAnalitycs {

    Context context;
    private FirebaseAnalytics mFirebaseAnalytics;

    public ClaseAnalitycs(Context context)
    {
        this.context=context;
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }


    void EventFirstOpen()
    {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}
