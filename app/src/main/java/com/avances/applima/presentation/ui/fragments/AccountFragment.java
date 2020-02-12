package com.avances.applima.presentation.ui.fragments;

import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.avances.applima.R;
import com.avances.applima.domain.model.UserPreference;
import com.avances.applima.presentation.ui.activities.EditProfileActivity;
import com.avances.applima.presentation.ui.activities.PreferencesActivity;
import com.avances.applima.presentation.ui.dialogfragment.CerrarSesionDialog;
import com.avances.applima.presentation.ui.dialogfragment.ValoraAppDialog;
import com.avances.applima.presentation.utils.Helper;

public class AccountFragment extends BaseFragment implements View.OnClickListener {


    private Button btn;
    private boolean playPause;
    private MediaPlayer mediaPlayer;
    private ProgressDialog progressDialog;
    private boolean initialStage = true;


    LinearLayout llEditarPerfil, llCerrarSesion, llPreferencias, llValoraApp;
    TextView tvUserName;
    ImageView ivUserImage;


    void showValoraApp() {
        ValoraAppDialog df = new ValoraAppDialog();
        // df.setArguments(args);
        df.show(getFragmentManager(), "ValoraAppDialog");
    }

    void showCerrarSesion() {
        CerrarSesionDialog df = new CerrarSesionDialog();
        // df.setArguments(args);
        df.show(getFragmentManager(), "CerrarSesionDialog");
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
                showValoraApp();
              /*  if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }*/
                break;
            case R.id.llCerrarSesion:
                showCerrarSesion();
               // mediaPlayer = new MediaPlayer();
               // mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
               // playAudio("http://200.37.138.11:8087/AppLimaFile/File/6365F336-4D57-4B77-BB91-D36C4303280F.mp3?version=1");

                break;


        }


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
                        btn.setText("Launch Streaming");
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

    @Override
    public void onPause() {
        super.onPause();

        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    void initUI(View v) {
        llEditarPerfil = (LinearLayout) v.findViewById(R.id.llEditarPerfil);
        llCerrarSesion = (LinearLayout) v.findViewById(R.id.llCerrarSesion);
        llPreferencias = (LinearLayout) v.findViewById(R.id.llPreferencias);
        llValoraApp = (LinearLayout) v.findViewById(R.id.llValoraApp);
        tvUserName = (TextView) v.findViewById(R.id.tvUserName);
        ivUserImage = (ImageView) v.findViewById(R.id.ivUserImage);

        llEditarPerfil.setOnClickListener(this);
        llCerrarSesion.setOnClickListener(this);
        llPreferencias.setOnClickListener(this);
        llValoraApp.setOnClickListener(this);

        progressDialog= new ProgressDialog(getContext());
    }


    void setUserInfo() {
        UserPreference userPreference = Helper.getUserAppPreference(getContext());
        if (userPreference.isLogged()) {
            tvUserName.setText(userPreference.getName());
            Helper.urlToImageView(userPreference.getImage(), ivUserImage, getContext());
        }
        else {
           // GoAseconds(null);
        }
    }



    void GoAseconds(Bundle bundle) {

        FragmentManager fragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SecondsToOfferFragment accountFragment = new SecondsToOfferFragment();
       // accountFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();
    }

    void cliclListeners() {
        llEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String hola = "hola";


            }
        });

        llCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hola = "hola";

            }
        });

        llPreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hola = "hola";

            }
        });

        llValoraApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String hola = "hola";
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View x = inflater.inflate(R.layout.account_fragment, null);

        initUI(x);

        setUserInfo();


        //  cliclListeners();
/*
     //   btn = (Button) x.findViewById(R.id.audioStreamBtn);
     //   progressDialog = new ProgressDialog(getContext());

      //  btn.setVisibility(View.GONE);



        String postFijo="(format=m3u8-aapl-v3)";

        String linkk_1080="http://ataplprodeusms-usea.streaming.media.azure.net/2e48ba00-7182-449a-8fe2-64a9ebb1f72a/cacao_tarapoto.ism/manifest"+postFijo;

        String linkk_720="http://ataplprodeusms-usea.streaming.media.azure.net/2e48ba00-7182-449a-8fe2-64a9ebb1f72a/cacao_tarapoto.ism/manifest"+postFijo;


    //   VideoView vidView = (VideoView)x.findViewById(R.id.myVideo);

     //   String vidAddress = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
        Uri vidUri = Uri.parse(linkk_1080);

    //    vidView.setVideoURI(vidUri);

    //    vidView.start();



        MediaController vidControl = new MediaController(getContext());

     //   vidControl.setAnchorView(vidView);
      //  vidView.setMediaController(vidControl);




        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String postFijo="(format=m3u8-aapl-v3)";
                String miaudio="http://ataplprodeusms-usea.streaming.media.azure.net/3922eef6-4ba0-4a8f-abee-85a48be82dcd/cacao_tarapoto.ism/manifest"+postFijo;

               // String miaudio="http://ataplprodeusms-usea.streaming.media.azure.net/3922eef6-4ba0-4a8f-abee-85a48be82dcd/cacao_tarapoto.ism/manifest";

                playAudio(miaudio);
            }
        });
*/

        return x;

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


}
