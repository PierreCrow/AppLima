package com.avances.lima.presentation.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.lima.R;
import com.avances.lima.domain.model.Place;
import com.avances.lima.presentation.presenter.PlacePresenter;
import com.avances.lima.presentation.ui.activities.PlaceDetailActivity;
import com.avances.lima.presentation.ui.adapters.PlacesVerticalListDataAdapter;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.view.PlaceView;

import java.util.List;

import butterknife.BindView;

public class PlacesFragment extends BaseFragment implements
        View.OnClickListener,
        PlacesVerticalListDataAdapter.OnImperdiblesVerticalListClickListener, PlaceView {

    @BindView(R.id.llEditarPerfil)

    TextView btnMenosImperdibles;
    RecyclerView rv_imperdibles;
    PlacePresenter placePresenter;
    List<Place> places;

    private PlacesVerticalListDataAdapter.OnImperdiblesVerticalListClickListener mlistener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View x = inflater.inflate(R.layout.imperdibles_fragment, null);
        initUI(x);
        clickEvents();
        loadPresenter();
        return x;

    }

    void initUI(View v) {
        btnMenosImperdibles = (TextView) v.findViewById(R.id.btnMenosImperdibles);
        rv_imperdibles = (RecyclerView) v.findViewById(R.id.rv_imperdibles);
        mlistener = this;
    }

    @Override
    public void onImperdiblesVerticalListClicked(View v, Integer position) {
        Place place = places.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("place", place);
        bundle.putBoolean("fromDistrit",false);
        loadPlaceDetailFragment(bundle);
    }

    void loadPlaceDetailFragment(Bundle bundle) {

        next(PlaceDetailActivity.class,getContext(),bundle);
    }

    void loadPresenter() {
        placePresenter = new PlacePresenter();
        placePresenter.addView(this);
        placePresenter.getPlaces(Constants.STORE.DB);
    }

    @Override
    public void placeListLoaded(List<Place> places) {
        this.places = places;

        boolean userHasLocation;

        if (Helper.getUserAppPreference(getContext()).isHasLocation()) {
            if (Helper.gpsIsEnabled(getContext())) {
                userHasLocation = true;
            } else {
                userHasLocation = false;
            }
        } else {
            userHasLocation = false;
        }


        PlacesVerticalListDataAdapter routesHorizontalDataAdapter = new PlacesVerticalListDataAdapter(mlistener, getContext(), places, userHasLocation);

        rv_imperdibles.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        rv_imperdibles.setLayoutManager(mLayoutManager);
        rv_imperdibles.setAdapter(routesHorizontalDataAdapter);


    }

    @Override
    public void placeCreated(String message) {

    }

    @Override
    public void placeUpdated(String message) {

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

    public interface GoToTabHomeFragment {
        public void goToHome();
    }

    void sendCallback() {
        Activity ahhh = getActivity();
        //   GastosFragment dda = (GastosFragment)getFragmentManager().findFragmentById(R.id.containerView);
        //  List<Fragment> ah=dda.getChildFragmentManager().getFragments();
        //  Fragment ahhh=ah.get(0);

        if (ahhh instanceof GoToTabHomeFragment) {
            ((GoToTabHomeFragment) ahhh).goToHome();
        }

    }


    @Override
    public void onClick(View view) {

    }


    @Override
    public void onPause() {
        super.onPause();

    }


    void clickEvents() {
        btnMenosImperdibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendCallback();

            }
        });


    }


    void GoBack() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //   fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_out_down);
        TabHome tabHome = new TabHome();
        fragmentTransaction.replace(R.id.containerView, tabHome);
        fragmentTransaction.commit();
    }




}
