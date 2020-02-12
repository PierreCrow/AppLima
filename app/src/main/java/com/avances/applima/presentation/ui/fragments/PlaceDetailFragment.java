package com.avances.applima.presentation.ui.fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Location;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.avances.applima.R;
import com.avances.applima.domain.model.Place;
import com.avances.applima.presentation.ui.activities.EditProfileActivity;
import com.avances.applima.presentation.ui.activities.PreferencesActivity;
import com.avances.applima.presentation.ui.dialogfragment.GoToMapDialog;
import com.avances.applima.presentation.utils.Helper;

public class PlaceDetailFragment extends BaseFragment implements View.OnClickListener {


    ImageView ivBack, ivPlaceImage;
    LinearLayout llIrAMapa, llDistance;

    int HOME = 1, DETALLE_DISTRITO = 2, IMPERDIBLES_LISTA = 3;
    int variable = 1;

    TextView tvPlaceName, tvPlaceDescription, tvKilometers, tvPlacePhone, tvPlaceWebPage, tvPlaceAddress;

    ImageView ivInterviewedImage;
    TextView tvInterviewedTittle, tvInterviewedName;
    LinearLayout llAudio;
    Place place;
    private ProgressDialog progressDialog;

    private boolean playPause;
    private MediaPlayer mediaPlayer;
    private boolean initialStage = true;

    public interface goBack {
        public void gotoBack();
    }

    void sendCallback() {
        Activity ahhh = getActivity();
        //   GastosFragment dda = (GastosFragment)getFragmentManager().findFragmentById(R.id.containerView);
        //  List<Fragment> ah=dda.getChildFragmentManager().getFragments();
        //  Fragment ahhh=ah.get(0);

        if (ahhh instanceof goBack) {
            ((goBack) ahhh).gotoBack();
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.llEditarPerfil:
                next(EditProfileActivity.class, getContext(), null);
                break;
            case R.id.llPreferencias:
                next(PreferencesActivity.class, getContext(), null);
                break;
            case R.id.llValoraApp:

                break;
            case R.id.llCerrarSesion:

                break;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
    }


    void initUI(View v) {

        Bundle bundle = getArguments();
        place = (Place) bundle.getSerializable("place");

        progressDialog= new ProgressDialog(getContext());

        ivBack = (ImageView) v.findViewById(R.id.ivBack);
        ivPlaceImage = (ImageView) v.findViewById(R.id.ivPlaceImage);
        llIrAMapa = (LinearLayout) v.findViewById(R.id.llIrAMapa);
        llDistance = (LinearLayout) v.findViewById(R.id.llDistance);
        tvPlaceName = (TextView) v.findViewById(R.id.tvPlaceName);
        tvKilometers = (TextView) v.findViewById(R.id.tvKilometers);
        tvPlacePhone = (TextView) v.findViewById(R.id.tvPlacePhone);
        tvPlaceWebPage = (TextView) v.findViewById(R.id.tvPlaceWebPage);
        tvPlaceAddress = (TextView) v.findViewById(R.id.tvPlaceAddress);
        tvInterviewedName = (TextView) v.findViewById(R.id.tvInterviewedName);
        tvInterviewedTittle = (TextView) v.findViewById(R.id.tvInterviewedTittle);
        llAudio = (LinearLayout) v.findViewById(R.id.llAudio);
        ivInterviewedImage= (ImageView) v.findViewById(R.id.ivInterviewedImage);

        tvPlaceDescription = (TextView) v.findViewById(R.id.tvPlaceDescription);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

    }


    void SetFields() {
        if (!Helper.gpsIsEnabled(getContext()) && Helper.getUserAppPreference(getContext()).isHasLocation()) {
        /*    Location userLocation = new Location("");
            userLocation.setLatitude(Double.parseDouble(Helper.getUserAppPreference(getContext()).getLat()));
            userLocation.setLongitude(Double.parseDouble(Helper.getUserAppPreference(getContext()).getLng()));

            Location placeLocation = new Location("");
            placeLocation.setLatitude(Double.parseDouble(place.getLat()));
            placeLocation.setLongitude(Double.parseDouble(place.getLng()));

            float distanceInMeters = userLocation.distanceTo(placeLocation);
            float Kilometers =distanceInMeters/1000;

            String kilometers = Helper.convertTwoDecimals(Kilometers);
            tvKilometers.setText(kilometers+"km");*/
        } else {
            llDistance.setVisibility(View.GONE);
        }


        tvPlaceName.setText(place.getTittle());
        tvPlaceDescription.setText(place.getDetail());
        Helper.urlToImageView(place.getImageList().get(0), ivPlaceImage, getContext());
        tvPlaceAddress.setText(place.getAddress());
        tvPlaceWebPage.setText(place.getWebPage());
        tvPlacePhone.setText(place.getPhone());
        tvInterviewedTittle.setText(place.getInterviewed().get(0));
        tvInterviewedName.setText(place.getInterviewed().get(1));
        Helper.urlToImageView(place.getInterviewed().get(2),ivInterviewedImage,getContext());

    }



    class Player extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {
            Boolean prepared = false;

            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        initialStage = true;
                        playPause = true;
                      //  btn.setText("Launch Streaming");
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer.prepare();
                prepared = true;

            } catch (Exception e) {
                Log.e("MyAudioStreamingApp", e.getMessage());
                prepared = false;
            }

            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (progressDialog.isShowing()) {
                progressDialog.cancel();
            }

            mediaPlayer.start();
            initialStage = false;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Buffering...");
            progressDialog.show();
        }
    }

    void playAudio(String audioHttpMp3) {
        if (!playPause) {
            //  btn.setText("Pause Streaming");

            if (initialStage) {
                new Player().execute(audioHttpMp3);
            } else {
                if (!mediaPlayer.isPlaying())
                    mediaPlayer.start();
            }

            playPause = true;

        } else {
            //  btn.setText("Launch Streaming");

            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }

            playPause = false;
        }
    }

    void clickEvents() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GoBack();

            }
        });

        llIrAMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                GoToMapDialog df = new GoToMapDialog();
                //  df.setArguments(args);
                df.show(getFragmentManager(), "tag");

            }
        });

        llAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                playAudio(place.getInterviewed().get(3));

            }
        });
    }


    void GoBack() {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (variable == HOME) {
            TabHome accountFragment = new TabHome();
            fragmentTransaction.replace(R.id.containerView, accountFragment);
            fragmentTransaction.commit();
        } else {
            if (variable == DETALLE_DISTRITO) {
                DistritDetailFragment accountFragment = new DistritDetailFragment();
                fragmentTransaction.replace(R.id.containerView, accountFragment);
                fragmentTransaction.commit();
            } else {
                if (variable == IMPERDIBLES_LISTA) {
                    ImperdiblesFragment accountFragment = new ImperdiblesFragment();
                    fragmentTransaction.replace(R.id.containerView, accountFragment);
                    fragmentTransaction.commit();
                }
            }
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View x = inflater.inflate(R.layout.place_detail, null);

        initUI(x);

        clickEvents();

        SetFields();

        return x;

    }


}
