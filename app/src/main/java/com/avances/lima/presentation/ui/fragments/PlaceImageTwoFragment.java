package com.avances.lima.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.avances.lima.R;
import com.avances.lima.presentation.ui.activities.PlaceDetailActivity;
import com.avances.lima.presentation.utils.Helper;

import butterknife.BindView;

public class PlaceImageTwoFragment extends BaseFragment {

    @BindView(R.id.place_image_view)
    ImageView place_image_view;

    @Override
    public void onPause() {
        super.onPause();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.place_image, null);
        injectView(x);
        initUI();
        return x;
    }
    private void initUI() {
        Helper.urlToImageView(PlaceDetailActivity.place.getImageList().get(1), place_image_view, getContext());
    }
}