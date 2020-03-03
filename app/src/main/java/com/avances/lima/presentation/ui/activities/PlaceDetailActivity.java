package com.avances.lima.presentation.ui.activities;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.avances.lima.R;
import com.avances.lima.domain.model.Place;
import com.avances.lima.presentation.presenter.PlacePresenter;
import com.avances.lima.presentation.ui.fragments.PlaceImageOneFragment;
import com.avances.lima.presentation.ui.fragments.PlaceImageThreeFragment;
import com.avances.lima.presentation.ui.fragments.PlaceImageTwoFragment;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.utils.SingleClick;
import com.avances.lima.presentation.view.PlaceView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.material.tabs.TabLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;

public class PlaceDetailActivity extends BaseActivity
        implements PlaceView {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.llIrAMapa)
    LinearLayout llIrAMapa;
    @BindView(R.id.llDistance)
    LinearLayout llDistance;
    @BindView(R.id.tvPlaceName)
    TextView tvPlaceName;
    @BindView(R.id.tvKilometers)
    TextView tvKilometers;
    @BindView(R.id.tvPlacePhone)
    TextView tvPlacePhone;
    @BindView(R.id.tvPlaceWebPage)
    TextView tvPlaceWebPage;
    @BindView(R.id.tvPlaceAddress)
    TextView tvPlaceAddress;
    @BindView(R.id.tvInterviewedName)
    TextView tvInterviewedName;
    @BindView(R.id.tvInterviewedTittle)
    TextView tvInterviewedTittle;
    @BindView(R.id.llAudio)
    LinearLayout llAudio;
    @BindView(R.id.llGoToCall)
    LinearLayout llGoToCall;
    @BindView(R.id.llGoToWebPage)
    LinearLayout llGoToWebPage;
    @BindView(R.id.llGoToMaps)
    LinearLayout llGoToMaps;
    @BindView(R.id.ivInterviewedImage)
    ImageView ivInterviewedImage;
    @BindView(R.id.ivShare)
    ImageView ivShare;
    @BindView(R.id.ivLike)
    ImageView ivLike;
    @BindView(R.id.ivPlayOrPause)
    ImageView ivPlayPause;
    @BindView(R.id.tvPlaceDescription)
    TextView tvPlaceDescription;


    public static Place place;
    private ProgressDialog progressDialog;

    private boolean playPause;
    private MediaPlayer mediaPlayer;
    private boolean initialStage = true;

    PlacePresenter placePresenter;
    boolean favoritePlace;

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3;
    Bundle stateScreen;
    boolean fromDistrit;
    private ViewFlipper myViewFlipper;
    private float initialXPoint;
    SingleClick singleClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.place_detail_activity);

        injectView();
        initUI();
        loadPresenter();
        viewValidations();
        SetFields();
    }

    private void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {

                switch (v.getId()) {
                    case R.id.ivBack:
                        GoBack();
                        break;
                    case R.id.llIrAMapa:
                        goToGeo();
                        break;
                    case R.id.llAudio:
                        playAudio(place.getInterviewed().get(3));
                        if (playPause) {
                            ivPlayPause.setImageResource(R.drawable.audiopauseicon);
                        } else {
                            ivPlayPause.setImageResource(R.drawable.ic_audio);
                        }
                        break;
                    case R.id.ivLike:
                        if (place.isFavorite()) {
                            place.setFavorite(false);
                            favoritePlace = false;
                            ivLike.setImageResource(R.drawable.ic_add_favorite);
                        } else {
                            place.setFavorite(true);
                            favoritePlace = true;
                            ivLike.setImageResource(R.drawable.corazonlleno);
                        }
                        placePresenter.updatePlaceFavorite(place, favoritePlace, Constants.STORE.DB);
                        break;
                    case R.id.ivShare:
                        if (!hasStoragePermission()) {
                            ActivityCompat.requestPermissions(PlaceDetailActivity.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                            Manifest.permission.READ_EXTERNAL_STORAGE},
                                    Constants.REQUEST_CODES.REQUEST_CODE_STORAGE);
                        } else {
                            shareeeee();
                        }
                        break;
                    case R.id.llGoToCall:
                        if (hasCallPermission()) {
                            goCall();
                        } else {
                            ActivityCompat.requestPermissions(PlaceDetailActivity.this,
                                    new String[]{Manifest.permission.CALL_PHONE},
                                    1234);
                        }
                        break;
                    case R.id.llGoToMaps:
                        goToGeo();
                        break;
                    case R.id.llGoToWebPage:
                        if (place.getWebPage() != null) {
                            if (!place.getWebPage().equals("")) {
                                Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
                                // openURL.setData(Uri.parse("http://www.google.com"));
                                openURL.setData(Uri.parse(place.getWebPage()));
                                startActivity(openURL);
                            }
                        }
                        break;
                }
            }
        };
    }


    void loadPresenter() {
        placePresenter = new PlacePresenter();
        placePresenter.addView(this);
    }


    void initUI() {

        Bundle bundle = getIntent().getBundleExtra("extra");
        place = (Place) bundle.getSerializable("place");
        stateScreen = (Bundle) bundle.getBundle("state");
        fromDistrit = (Boolean) bundle.getBoolean("fromDistrit");

        onClickListener();
        ivBack.setOnClickListener(singleClick);
        llIrAMapa.setOnClickListener(singleClick);
        llAudio.setOnClickListener(singleClick);
        ivLike.setOnClickListener(singleClick);
        ivShare.setOnClickListener(singleClick);
        llGoToCall.setOnClickListener(singleClick);
        llGoToMaps.setOnClickListener(singleClick);
        llGoToWebPage.setOnClickListener(singleClick);

        viewPager = (ViewPager) findViewById(R.id.viewpagerPlace);
        CircleIndicator indicator = findViewById(R.id.indicator);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        progressDialog = new ProgressDialog(getApplicationContext());
        favoritePlace = place.isFavorite();

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        indicator.setViewPager(viewPager);

    }


    void viewValidations() {
        if (place.getWebPage() == null) {
            llGoToWebPage.setVisibility(View.GONE);
        }

        if (place.getPhone() == null) {
            llGoToCall.setVisibility(View.GONE);
        }

        if (place.getInterviewed().get(0) == null) {
            llAudio.setVisibility(View.GONE);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialXPoint = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float finalx = event.getX();
                if (initialXPoint > finalx) {
                    if (myViewFlipper.getDisplayedChild() == PlaceDetailActivity.place.getImageList().size())
                        break;
                    myViewFlipper.showNext();
                } else {
                    if (myViewFlipper.getDisplayedChild() == 0)
                        break;
                    myViewFlipper.showPrevious();
                }
                break;
        }
        return false;
    }


    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: {
                    return new PlaceImageOneFragment();
                }
                case 1: {
                    return new PlaceImageTwoFragment();
                }
                case 2: {
                    return new PlaceImageThreeFragment();
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }


    void SetFields() {
        if (!Helper.gpsIsEnabled(getApplicationContext()) && Helper.getUserAppPreference(getApplicationContext()).isHasLocation()) {
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
        //   Helper.urlToImageView(place.getImageList().get(0), ivPlaceImage, getApplicationContext());
        tvPlaceAddress.setText(place.getAddress());
        tvPlaceWebPage.setText(place.getWebPage());
        tvPlacePhone.setText(place.getPhone());
        tvInterviewedTittle.setText(place.getInterviewed().get(0));
        tvInterviewedName.setText(place.getInterviewed().get(1));
        Helper.urlToImageView(place.getInterviewed().get(2), ivInterviewedImage, getApplicationContext());

        if (favoritePlace) {
            ivLike.setImageResource(R.drawable.corazonlleno);
        }

    }


    @Override
    public void placeListLoaded(List<Place> places) {

    }

    @Override
    public void placeCreated(String message) {

    }

    @Override
    public void placeUpdated(String message) {

        //  ivLike.setEnabled(true);
        if (favoritePlace) {
            sendCallback(place);
            //    ivLike.setImageResource(R.drawable.corazonlleno);
        } else {
            //  ivLike.setImageResource(R.drawable.ic_add_favorite);
        }
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

    @Override
    public Context getContext() {
        return this;
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

//            progressDialog.setMessage("Buffering...");
            //       progressDialog.show();
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


    void goToGeo() {

        // String lat=place.getLat();
        // String lng=place.getLng();
        String lat = "-12.072063";
        String lng = "-77.015053";


        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-12.072063,-77.015053?q=-12.072063,-77.015053(Label+Nombreee)"));
        startActivity(intent);

    }

    public boolean hasCallPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
        ) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case Constants.REQUEST_CODES.REQUEST_CODE_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    shareeeee();

                } else {
                }
                return;
            }
        }

    }

    public boolean hasStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ) {
            return false;
        } else {
            return true;
        }
    }


    void goCall() {
        String phoneNumber = place.getPhone();

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);
    }

    private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


    public Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        try {
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }


    void shareeeee() {


        Glide.with(this)
                .load(place.getImageList().get(0))
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Bitmap image = null;
                        image = resource;

                        Uri URI_A_COMPARTIR = getImageUri(getApplicationContext(), image);


                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);

                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_STREAM, URI_A_COMPARTIR);
                        intent.putExtra(Intent.EXTRA_TEXT, "Lugar Turistico");
                        startActivity(Intent.createChooser(intent, "Compartelo"));
                    }
                });

        //   Bitmap b=getBitmapFromView(ivPlaceImage);
        //   Bitmap b=getBitmapFromURL(place.getImageList().get(0));
        //  String patheee = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), b, "Design", null);

        //   Uri URI_A_COMPARTIR = Uri.parse(patheee);

    }


    //--------------------------------------INTERFACEEEE

    public interface LikeAPlace {
        public void onLike(Place place);
    }

    void sendCallback(Place place) {

        //  MainActivity.sendCallback(place);

        Activity ahhh = new MainActivity();
        if (ahhh instanceof LikeAPlace) {
            ((LikeAPlace) ahhh).onLike(place);
        }

    }


    void GoBack() {

        if (fromDistrit) {
            SharedPreferences preferenciasssee = getContext().getSharedPreferences("PlaceDistritView", Context.MODE_PRIVATE);
            SharedPreferences.Editor editoriieei = preferenciasssee.edit();
            editoriieei.putBoolean("FromDistritDetail", true);
            // editoriieei.putString("idPlace", place.getId());
            editoriieei.apply();
        } else {
            SharedPreferences preferenciasssee = getContext().getSharedPreferences("PlaceDistritView", Context.MODE_PRIVATE);
            SharedPreferences.Editor editoriieei = preferenciasssee.edit();
            editoriieei.putBoolean("FromDistritDetail", false);
            // editoriieei.putString("idPlace", place.getId());
            editoriieei.apply();
        }
        finish();
    }


    @Override
    public void onPause() {
        super.onPause();
    }

}