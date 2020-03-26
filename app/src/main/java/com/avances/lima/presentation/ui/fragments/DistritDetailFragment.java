package com.avances.lima.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.lima.R;
import com.avances.lima.domain.model.DistritNeighborhood;
import com.avances.lima.domain.model.Place;
import com.avances.lima.presentation.presenter.PlacePresenter;
import com.avances.lima.presentation.ui.activities.DistritMapActivity;
import com.avances.lima.presentation.ui.activities.PlaceDetailActivity;
import com.avances.lima.presentation.ui.adapters.PlacesHorizontalListDataAdapter;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.utils.SingleClick;
import com.avances.lima.presentation.utils.TextureVideoView;
import com.avances.lima.presentation.view.PlaceView;
import com.vincan.medialoader.MediaLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DistritDetailFragment extends BaseFragment implements
        PlacesHorizontalListDataAdapter.OnImperdiblesHorizontalClickListener, PlaceView {

    @BindView(R.id.rvImperdibles)
    RecyclerView rvImperdibles;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvMoreImperdibles)
    TextView tvMoreImperdibles;
    @BindView(R.id.tvDistritName)
    TextView tvDistritName;
    @BindView(R.id.tvLongDescription)
    TextView tvLongDescription;
    @BindView(R.id.llIrAMapa)
    LinearLayout llIrAMapa;
    @BindView(R.id.rlDistritImage)
    ImageView rlDistritImage;
    @BindView(R.id.vvDistritVideo)
    TextureVideoView vvDistritVideo;

    public static DistritNeighborhood distritNeighborhood;
    private PlacesHorizontalListDataAdapter.OnImperdiblesHorizontalClickListener mlistenerImperdiblesHorizontal;
    PlacePresenter placePresenter;
    List<Place> places;
    List<String> idPlaces;
    public static Bundle stateScreen;
    SingleClick singleClick;
    String proxyUrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (stateScreen == null) {
            stateScreen = savedInstanceState;
        }
        View x = inflater.inflate(R.layout.distrit_detail, null);
        injectView(x);
        initUI(x);
        loadPresenter();
        setFields();
        return x;
    }

    private void initUI(View v) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            distritNeighborhood = (DistritNeighborhood) bundle.getSerializable("distritNeighborhood");
        }
        vvDistritVideo.setScaleType(TextureVideoView.ScaleType.TOP);

        if (Helper.isConnectedToInternet(getContext())) {
            rlDistritImage.setVisibility(View.GONE);
            vvDistritVideo.setVisibility(View.VISIBLE);
            playVideo(distritNeighborhood.getUrlVideo());
        }

        idPlaces = distritNeighborhood.getIdPlaceList();
        places = new ArrayList<>();
        onClickListener();
        ivBack.setOnClickListener(singleClick);
        tvMoreImperdibles.setOnClickListener(singleClick);
        llIrAMapa.setOnClickListener(singleClick);
        mlistenerImperdiblesHorizontal = this;
    }


    void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {
                switch (v.getId()) {
                    case R.id.ivBack:
                        vvDistritVideo.stop();
                        Helper.loadDistritFromHome(getContext());
                        loadHomeFragment();
                        break;
                    case R.id.tvMoreImperdibles:
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        PlacesFragment accountFragment = new PlacesFragment();
                        fragmentTransaction.replace(R.id.containerView, accountFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.llIrAMapa:
                        vvDistritVideo.stop();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("distritNeighborhood", distritNeighborhood);
                        next(DistritMapActivity.class, getContext(), bundle);
                        break;
                }
            }
        };
    }


    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        stateScreen = savedInstanceState;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        stateScreen = savedInstanceState;
    }


    void loadPresenter() {
        placePresenter = new PlacePresenter();
        placePresenter.addView(this);
        placePresenter.getPlaces(Constants.STORE.DB);
    }


    void playVideo(String video) {
        try {
            proxyUrl = MediaLoader.getInstance(getContext()).getProxyUrl(video);
            vvDistritVideo.setDataSource(proxyUrl);
            vvDistritVideo.setListener(new TextureVideoView.MediaPlayerListener() {
                @Override
                public void onVideoPrepared() {
                }

                @Override
                public void onVideoEnd() {
                    playVideo(distritNeighborhood.getUrlVideo());
                }
            });
            vvDistritVideo.play();
        } catch (Exception ex) {

        }
    }

    private void setFields() {
        tvDistritName.setText(distritNeighborhood.getDistrit());
        tvLongDescription.setText(distritNeighborhood.getCompleteDescription());
        Helper.urlToImageView(distritNeighborhood.getImageList().get(0), rlDistritImage, getContext());
    }


    void loadHomeFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TabHome accountFragment = new TabHome();
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onImperdiblesHorizontalClicked(View v, Integer position) {
        vvDistritVideo.stop();
        Place place = places.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("place", place);
        bundle.putBundle("state", stateScreen);
        bundle.putBoolean("fromDistrit", true);
        loadPlaceDetailFragment(bundle);
    }

    void loadPlaceDetailFragment(Bundle bundle) {
        next(PlaceDetailActivity.class, getContext(), bundle);
    }


    @Override
    public void placeListLoaded(List<Place> mPlaces) {
        boolean userHasLocation;
        for (Place place : mPlaces) {
            for (String idPlace : idPlaces) {
                if (idPlace.equals(place.getId())) {
                    places.add(place);
                }
            }
        }

        if (Helper.getUserAppPreference(getContext()).isHasLocation()) {
            if (Helper.gpsIsEnabled(getContext())) {
                userHasLocation = true;
            } else {
                userHasLocation = false;
            }
        } else {
            userHasLocation = false;
        }

        PlacesHorizontalListDataAdapter routesHorizontalDataAdapter = new PlacesHorizontalListDataAdapter(mlistenerImperdiblesHorizontal, getContext(), places, userHasLocation);
        rvImperdibles.setHasFixedSize(true);
        rvImperdibles.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvImperdibles.setAdapter(routesHorizontalDataAdapter);
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

}
