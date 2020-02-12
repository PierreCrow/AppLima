package com.avances.applima.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.avances.applima.R;
import com.avances.applima.presentation.ui.activities.PlaceDetailActivity;
import com.avances.applima.presentation.utils.Helper;

public class PlaceImageThreeFragment extends Fragment {


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

        initUI(x);

        clickEvents();

        return x;

    }

    private void initUI(View v) {
        place_image_view = (ImageView) v.findViewById(R.id.place_image_view);
       Helper.urlToImageView(PlaceDetailActivity.place.getImageList().get(2),place_image_view,getContext());
    }

    void clickEvents() {



    }


    void loadHomeFragment() {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_out_down);
        HomeFragment accountFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();
    }

}