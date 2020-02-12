package com.avances.applima.presentation.ui.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.applima.R;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.domain.model.DistritNeighborhood;
import com.avances.applima.domain.model.Place;
import com.avances.applima.presentation.presenter.DistritNeighborhoodPresenter;
import com.avances.applima.presentation.presenter.PlacePresenter;
import com.avances.applima.presentation.ui.activities.DistritMapActivity;
import com.avances.applima.presentation.ui.activities.PlaceDetailActivity;
import com.avances.applima.presentation.ui.adapters.ImperdiblesHorizontalListDataAdapter;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.utils.TextureVideoView;
import com.avances.applima.presentation.view.DistritNeighborhoodView;
import com.avances.applima.presentation.view.PlaceView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.yqritc.scalablevideoview.ScalableVideoView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DistritDetailFragment extends BaseFragment implements ImperdiblesHorizontalListDataAdapter.OnImperdiblesHorizontalClickListener, PlaceView {


    ArrayList<DbPlace> dbPlaces;
    RecyclerView rvImperdibles;
    ImageView ivBack;
    TextView tvMoreImperdibles, tvDistritName, tvLongDescription;
    LinearLayout llIrAMapa;
    public static DistritNeighborhood distritNeighborhood;
    ImageView rlDistritImage;
    private ImperdiblesHorizontalListDataAdapter.OnImperdiblesHorizontalClickListener mlistenerImperdiblesHorizontal;
    PlacePresenter placePresenter;
    List<Place> places;

    List<String> idPlaces;

    public static Bundle stateScreen;

    TextureVideoView vvDistritVideo;

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
     /*   savedInstanceState.putBoolean("MyBoolean", true);
        savedInstanceState.putDouble("myDouble", 1.9);
        savedInstanceState.putInt("MyInt", 1);
        savedInstanceState.putString("MyString", "Welcome back to Android");*/
        // etc.

        stateScreen = savedInstanceState;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
    /*    boolean myBoolean = savedInstanceState.getBoolean("MyBoolean");
        double myDouble = savedInstanceState.getDouble("myDouble");
        int myInt = savedInstanceState.getInt("MyInt");
        String myString = savedInstanceState.getString("MyString");*/
        stateScreen = savedInstanceState;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (stateScreen == null) {
            stateScreen = savedInstanceState;
        }


        View x = inflater.inflate(R.layout.distrit_detail, null);

        initUI(x);

        loadPresenter();

        //  loadshitdata();

        clickEvents();

        setFields();

        return x;

    }


    void loadPresenter() {
        placePresenter = new PlacePresenter();
        placePresenter.addView(this);
        placePresenter.getPlaces(Constants.STORE.DB);
    }

    private void initUI(View v) {

        Bundle bundle = getArguments();
        if (bundle != null) {
            distritNeighborhood = (DistritNeighborhood) bundle.getSerializable("distritNeighborhood");
        }

        idPlaces = distritNeighborhood.getIdPlaceList();
        places = new ArrayList<>();

        rvImperdibles = (RecyclerView) v.findViewById(R.id.rvImperdibles);
        ivBack = (ImageView) v.findViewById(R.id.ivBack);
        tvMoreImperdibles = (TextView) v.findViewById(R.id.tvMoreImperdibles);
        llIrAMapa = (LinearLayout) v.findViewById(R.id.llIrAMapa);
        tvDistritName = (TextView) v.findViewById(R.id.tvDistritName);
        tvLongDescription = (TextView) v.findViewById(R.id.tvLongDescription);
        rlDistritImage = (ImageView) v.findViewById(R.id.rlDistritImage);

        vvDistritVideo = (TextureVideoView) v.findViewById(R.id.vvDistritVideo);

        vvDistritVideo.setScaleType(TextureVideoView.ScaleType.TOP);


        if (Helper.isConnectedToInternet(getContext())) {
            rlDistritImage.setVisibility(View.GONE);
            vvDistritVideo.setVisibility(View.VISIBLE);
            //  vvDistritVideo= new TextureVideoView(getContext());
            playVideo(distritNeighborhood.getUrlVideo());
        } else {

        }


        mlistenerImperdiblesHorizontal = this;

    }


    void playVideo(String video) {
        try {
            // VideoView videoHolder = new VideoView(this);
            // setContentView(videoHolder);
            //   Uri video = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.videolima);
            //   String vidAddress = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
            Uri vidUri = Uri.parse(video);
            vvDistritVideo.setDataSource(video);//.setVideoURI(vidUri);

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

    public Bitmap getBitmapFromURL(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setFields() {
        tvDistritName.setText(distritNeighborhood.getDistrit());
        tvLongDescription.setText(distritNeighborhood.getCompleteDescription());


       /* Picasso.with(getContext())
                .load(distritNeighborhood.getImageList().get(0))
                .into((Target) rlDistritImage);
*/
      /*  Bitmap myImage = getBitmapFromURL(distritNeighborhood.getImageList().get(0));
        Drawable dr = new BitmapDrawable(myImage);
        rlDistritImage.setBackground(dr);*/

        Helper.urlToImageView(distritNeighborhood.getImageList().get(0), rlDistritImage, getContext());

    }

    void clickEvents() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                vvDistritVideo.stop();

                Helper.loadDistritFromHome(getContext());

                loadHomeFragment();

            }
        });


        tvMoreImperdibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_out_down);
                ImperdiblesFragment accountFragment = new ImperdiblesFragment();
                fragmentTransaction.replace(R.id.containerView, accountFragment);
                fragmentTransaction.commit();

            }
        });

        llIrAMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vvDistritVideo.stop();

                Bundle bundle = new Bundle();
                bundle.putSerializable("distritNeighborhood", distritNeighborhood);

                next(DistritMapActivity.class, getContext(), bundle);

                //    GoToMapDialog df = new GoToMapDialog();
                //  df.setArguments(args);
                //   df.show(getFragmentManager(), "tag");

            }
        });


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

   /*     FragmentManager fragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlaceDetailFragment accountFragment = new PlaceDetailFragment();
        accountFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();*/
    }


    @Override
    public void placeListLoaded(List<Place> mPlaces) {


        for (Place place : mPlaces) {
            for (String idPlace : idPlaces) {
                if (idPlace.equals(place.getId())) {
                    places.add(place);
                }
            }
        }

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


        ImperdiblesHorizontalListDataAdapter routesHorizontalDataAdapter = new ImperdiblesHorizontalListDataAdapter(mlistenerImperdiblesHorizontal, getContext(), places, userHasLocation);

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
