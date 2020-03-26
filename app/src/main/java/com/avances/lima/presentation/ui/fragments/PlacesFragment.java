package com.avances.lima.presentation.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.lima.R;
import com.avances.lima.domain.model.Place;
import com.avances.lima.presentation.presenter.PlacePresenter;
import com.avances.lima.presentation.ui.activities.PlaceDetailActivity;
import com.avances.lima.presentation.ui.adapters.PlacesVerticalListDataAdapter;
import com.avances.lima.presentation.ui.dialogfragment.FilterDialog;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.utils.SingleClick;
import com.avances.lima.presentation.view.PlaceView;

import java.util.List;

import butterknife.BindView;

public class PlacesFragment extends BaseFragment implements
        View.OnClickListener,
        PlacesVerticalListDataAdapter.OnImperdiblesVerticalListClickListener, PlaceView {

    @BindView(R.id.btnSedarch)
    ImageView ivFilter;
    @BindView(R.id.btnMenosImperdibles)
    TextView btnMenosImperdibles;
    @BindView(R.id.editTextSearchCode)
    TextView etBuscador;

   // TextView btnMenosImperdibles;
    RecyclerView rv_imperdibles;
    PlacePresenter placePresenter;
    List<Place> places;
    SingleClick singleClick;

    private PlacesVerticalListDataAdapter.OnImperdiblesVerticalListClickListener mlistener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.imperdibles_fragment, null);
        injectView(x);
        initUI(x);
        loadPresenter();
        return x;

    }

    void initUI(View v) {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {

                switch (v.getId()) {
                    case R.id.btnMenosImperdibles:
                        sendCallback();
                        break;
                    case R.id.btnSedarch:
                        loadFilterHomeFragment();
                        break;
                    case R.id.editTextSearchCode:
                        sendCallbackBuscador();
                        break;
                }
            }
        };
        btnMenosImperdibles.setOnClickListener(singleClick);
        ivFilter.setOnClickListener(singleClick);
        etBuscador.setOnClickListener(singleClick);
        rv_imperdibles = (RecyclerView) v.findViewById(R.id.rv_imperdibles);
        mlistener = this;
    }

    void loadFilterHomeFragment() {
        FilterDialog df = new FilterDialog();
        df.show(getFragmentManager(), "ClientDetail");
    }


    void sendCallbackBuscador() {
        Activity ahhh = getActivity();
        if (ahhh instanceof HomeFragment.GoToBuscador) {
            ((HomeFragment.GoToBuscador) ahhh).goToBuscador();
        }
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

}
