package com.avances.applima.presentation.ui.activities;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.avances.applima.R;
import com.avances.applima.domain.model.Place;
import com.avances.applima.presentation.presenter.PlacePresenter;
import com.avances.applima.presentation.ui.fragments.PlaceImageOneFragment;
import com.avances.applima.presentation.ui.fragments.PlaceImageThreeFragment;
import com.avances.applima.presentation.ui.fragments.PlaceImageTwoFragment;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.view.PlaceView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class PlaceDetailActivity extends BaseActivity implements PlaceView {



    ImageView ivBack, ivPlaceImage;
    LinearLayout llIrAMapa, llDistance;

    int HOME = 1, DETALLE_DISTRITO = 2, IMPERDIBLES_LISTA = 3;
    int variable = 1;

    TextView tvPlaceName, tvPlaceDescription, tvKilometers, tvPlacePhone, tvPlaceWebPage, tvPlaceAddress;

    ImageView ivInterviewedImage;
    TextView tvInterviewedTittle, tvInterviewedName;
    LinearLayout llAudio;
    public static Place place;
    private ProgressDialog progressDialog;

    private boolean playPause;
    private MediaPlayer mediaPlayer;
    private boolean initialStage = true;
    ImageView ivShare,ivLike;

    PlacePresenter placePresenter;
    boolean favoritePlace;
    LinearLayout llGoToCall,llGoToWebPage,llGoToMaps;

    public static TabLayout tabLayout;
    public static ViewPager viewPager;

    public static int int_items = 3;
    ImageView ivPlayPause;


    Bundle stateScreen;

    boolean fromDistrit;



    private ViewFlipper myViewFlipper;
    private float initialXPoint;
  /*  int[] image = { R.drawable.one_full, R.drawable.two_full,
            R.drawable.three_full, R.drawable.four_full, R.drawable.five_full,
            R.drawable.six_full, R.drawable.seven_full, R.drawable.eight_full,
            R.drawable.nine_full, R.drawable.ten_full };
*/


    void loadPresenter()
    {
        placePresenter= new PlacePresenter();
        placePresenter.addView(this);
    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }


    void initUI() {

        Bundle bundle = getIntent().getBundleExtra("extra");
        place = (Place) bundle.getSerializable("place");
        stateScreen=(Bundle)bundle.getBundle("state") ;
        fromDistrit=(Boolean)bundle.getBoolean("fromDistrit") ;

    //    progressDialog= new ProgressDialog(getContext());

        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivPlaceImage = (ImageView) findViewById(R.id.ivPlaceImage);
        llIrAMapa = (LinearLayout) findViewById(R.id.llIrAMapa);
        llDistance = (LinearLayout) findViewById(R.id.llDistance);
        tvPlaceName = (TextView) findViewById(R.id.tvPlaceName);
        tvKilometers = (TextView) findViewById(R.id.tvKilometers);
        tvPlacePhone = (TextView) findViewById(R.id.tvPlacePhone);
        tvPlaceWebPage = (TextView) findViewById(R.id.tvPlaceWebPage);
        tvPlaceAddress = (TextView) findViewById(R.id.tvPlaceAddress);
        tvInterviewedName = (TextView) findViewById(R.id.tvInterviewedName);
        tvInterviewedTittle = (TextView) findViewById(R.id.tvInterviewedTittle);
        llAudio = (LinearLayout) findViewById(R.id.llAudio);
        llGoToCall = (LinearLayout) findViewById(R.id.llGoToCall);
        llGoToWebPage = (LinearLayout) findViewById(R.id.llGoToWebPage);
        llGoToMaps = (LinearLayout) findViewById(R.id.llGoToMaps);
        ivInterviewedImage= (ImageView) findViewById(R.id.ivInterviewedImage);
        ivShare= (ImageView) findViewById(R.id.ivShare);
        ivLike= (ImageView) findViewById(R.id.ivLike);

        ivPlayPause=(ImageView) findViewById(R.id.ivPlayOrPause);

        tvPlaceDescription = (TextView) findViewById(R.id.tvPlaceDescription);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        progressDialog= new ProgressDialog(getApplicationContext());
        favoritePlace=place.isFavorite();

        viewPager = (ViewPager) findViewById(R.id.viewpagerPlace);

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));


        CircleIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);




/*
        myViewFlipper = (ViewFlipper) findViewById(R.id.myflipper);

        for (int i = 0; i < PlaceDetailActivity.place.getImageList().size(); i++) {
            ImageView imageView = new ImageView(PlaceDetailActivity.this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Helper.urlToImageView(PlaceDetailActivity.place.getImageList().get(i),imageView,getContext());
          // imageView.setImageResource(image[i]);
            myViewFlipper.addView(imageView);
        }
*/
    }


    void viewValidations()
    {
        if(place.getWebPage()==null)
        {
            llGoToWebPage.setVisibility(View.GONE);
        }

        if(place.getPhone()==null)
        {
            llGoToCall.setVisibility(View.GONE);
        }

        if(place.getInterviewed().get(0)==null)
        {
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
        Helper.urlToImageView(place.getInterviewed().get(2),ivInterviewedImage,getApplicationContext());

        if(favoritePlace)
        {
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

        if(favoritePlace)
        {
            sendCallback(place);
        //    ivLike.setImageResource(R.drawable.corazonlleno);
        }
        else
        {
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


              //  GoToMapDialog df = new GoToMapDialog();
                //  df.setArguments(args);
              //  df.show(getSupportFragmentManager(), "tag");

                goToGeo();

            }
        });

        llAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                playAudio(place.getInterviewed().get(3));

                if(playPause)
                {
                    ivPlayPause.setImageResource(R.drawable.audiopauseicon);
                }
                else
                {
                    ivPlayPause.setImageResource(R.drawable.ic_audio);
                }


            }
        });

        ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  ivLike.setEnabled(false);

                if(place.isFavorite())
                {
                    place.setFavorite(false);
                    favoritePlace=false;
                    ivLike.setImageResource(R.drawable.ic_add_favorite);
                }
                else
                {
                    place.setFavorite(true);
                    favoritePlace=true;
                    ivLike.setImageResource(R.drawable.corazonlleno);
                }
                placePresenter.updatePlaceFavorite(place,favoritePlace, Constants.STORE.DB);

            }
        });

        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  shareItem(place.getImageList().get(0));


                if (!hasStoragePermission()) {
                    ActivityCompat.requestPermissions(PlaceDetailActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE},
                            Constants.REQUEST_CODES.REQUEST_CODE_STORAGE);
                }
                else
                {
                    shareeeee();
                }



            }
        });


        llGoToCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(hasCallPermission())
                {
                    goCall();
                }
                else
                {
                    ActivityCompat.requestPermissions(PlaceDetailActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1234);
                }



            }

        });



        llGoToMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  GoToMapDialog df = new GoToMapDialog();
                //  df.setArguments(args);
               // df.show(getSupportFragmentManager(), "tag");

                goToGeo();

            }
        });

        llGoToWebPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(place.getWebPage()!=null)
                {
                    if(!place.getWebPage().equals(""))
                    {
                        Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
                       // openURL.setData(Uri.parse("http://www.google.com"));
                        openURL.setData(Uri.parse(place.getWebPage()));
                        startActivity(openURL);
                    }
                }





            }
        });
    }


    void goToGeo()
    {

       // String lat=place.getLat();
        // String lng=place.getLng();
        String lat="-12.072063";
        String lng="-77.015053";


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
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    shareeeee();

                } else {
                    // permission denied, boo! Disable the

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


    void goCall()
    {
        String phoneNumber=place.getPhone();

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+phoneNumber));
        startActivity(callIntent);
    }

    private Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
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
            File file =  new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    public void shareItem(String url) {
        Picasso.with(getApplicationContext()).load(url).into(new Target() {
            @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/*");
                i.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap));
                startActivity(Intent.createChooser(i, "Share Image"));
            }
            @Override public void onBitmapFailed(Drawable errorDrawable) { }
            @Override public void onPrepareLoad(Drawable placeHolderDrawable) { }
        });
    }



    void shareeeee()
    {






        Glide.with(this)
                .load(place.getImageList().get(0))
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Bitmap image=null;
                        image=resource;

                        Uri URI_A_COMPARTIR = getImageUri(getApplicationContext(),image);


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


        //if e venido de distrit que haga eso

        //si vine de otro lado que le de false

        if(fromDistrit)
        {
            SharedPreferences preferenciasssee = getContext().getSharedPreferences("PlaceDistritView", Context.MODE_PRIVATE);
            SharedPreferences.Editor editoriieei = preferenciasssee.edit();
            editoriieei.putBoolean("FromDistritDetail", true);
            // editoriieei.putString("idPlace", place.getId());
            editoriieei.apply();
        }
        else
        {
            SharedPreferences preferenciasssee = getContext().getSharedPreferences("PlaceDistritView", Context.MODE_PRIVATE);
            SharedPreferences.Editor editoriieei = preferenciasssee.edit();
            editoriieei.putBoolean("FromDistritDetail", false);
            // editoriieei.putString("idPlace", place.getId());
            editoriieei.apply();
        }






     /*   SharedPreferences preferences = getContext().getSharedPreferences("PlaceDistritView", Context.MODE_PRIVATE);
        boolean hh=preferences.getBoolean("FromDistritDetail",false);
        String idPlace=preferences.getString("idPlace",place.getId());*/


        finish();
/*
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
        }*/
    }



    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.place_detail);

        initUI();

        loadPresenter();

        viewValidations();

        clickEvents();

        SetFields();

    }


}