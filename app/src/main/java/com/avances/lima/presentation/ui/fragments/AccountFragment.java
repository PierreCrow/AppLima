package com.avances.lima.presentation.ui.fragments;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.avances.lima.R;
import com.avances.lima.domain.model.UserPreference;
import com.avances.lima.domain.model.Usuario;
import com.avances.lima.presentation.presenter.UsuarioPresenter;
import com.avances.lima.presentation.ui.activities.EditProfileActivity;
import com.avances.lima.presentation.ui.activities.MainActivity;
import com.avances.lima.presentation.ui.activities.PreferencesActivity;
import com.avances.lima.presentation.ui.dialogfragment.LogoutDialog;
import com.avances.lima.presentation.ui.dialogfragment.PickCameraGalleryDialog;
import com.avances.lima.presentation.ui.dialogfragment.RateAppDialog;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.utils.ImportPhotoBottomFragment;
import com.avances.lima.presentation.utils.SingleClick;
import com.avances.lima.presentation.utils.TransparentProgressDialog;
import com.avances.lima.presentation.view.UsuarioView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;


public class AccountFragment extends BaseFragment implements ImportPhotoBottomFragment.Listener, UsuarioView {

    @BindView(R.id.llEditarPerfil)
    LinearLayout llEditarPerfil;
    @BindView(R.id.llCerrarSesion)
    LinearLayout llCerrarSesion;
    @BindView(R.id.llPreferencias)
    LinearLayout llPreferencias;
    @BindView(R.id.llValoraApp)
    LinearLayout llValoraApp;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    //  @BindView(R.id.ivUserImage)
    public static ImageView ivUserImage;
    @BindView(R.id.lineOne)
    View lineOne;
    @BindView(R.id.lineTwo)
    View lineTwo;
    @BindView(R.id.lineThree)
    View lineThree;

    @BindView(R.id.ivBackgroundUserImage)
    ImageView ivBackgroundUserImage;

    byte[] pictureData;
    SingleClick singleClick;
    public static Context context;
    public static String token;

    public static UsuarioPresenter usuarioPresenter;
    public static boolean goToAccount = false;
    TransparentProgressDialog loading;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View x = inflater.inflate(R.layout.account_fragment, null);

        injectView(x);
        initUI(x);
        setUserInfo();
        loadPresenter();

        return x;
    }




    private void loadPresenter() {
        usuarioPresenter = new UsuarioPresenter();
        usuarioPresenter.addView(this);
    }

    public static void goPicture(String encodedImage) {

        goToAccount = true;
        usuarioPresenter.uploadPicture(token, "appLimaUser.jpg", encodedImage);
    }

    public static Bitmap mifoto = null;


    void initUI(View x) {

        loading = new TransparentProgressDialog(getContext());
        context = getContext();
        ivUserImage = (ImageView) x.findViewById(R.id.ivUserImage);

        onClickListener();

        llEditarPerfil.setOnClickListener(singleClick);
        llCerrarSesion.setOnClickListener(singleClick);
        llPreferencias.setOnClickListener(singleClick);
        llValoraApp.setOnClickListener(singleClick);
        ivUserImage.setOnClickListener(singleClick);
    }


    void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {

                switch (v.getId()) {
                    case R.id.llEditarPerfil:
                        if (refreshViewToNoInternet()) {
                            refreshViews();
                        } else {
                            next(EditProfileActivity.class, getContext(), null);
                        }
                        break;
                    case R.id.llPreferencias:
                        if (refreshViewToNoInternet()) {
                            refreshViews();
                        } else {
                            next(PreferencesActivity.class, getContext(), null);
                        }
                        break;
                    case R.id.llValoraApp:
                        if (refreshViewToNoInternet()) {
                            refreshViews();
                        } else {
                            showValoraApp();
                        }
                        break;
                    case R.id.llCerrarSesion:
                        showCerrarSesion();
                        break;
                    case R.id.ivUserImage:
                        if (Helper.getUserAppPreference(getContext()).getRegisterLoginType().equals(Constants.REGISTER_TYPES.EMAIL)) {
                            showPickImportPhotoType();
                        }
                        break;
                }
            }
        };
    }


    void showValoraApp() {
        RateAppDialog df = new RateAppDialog();
        // df.setArguments(args);
        df.show(getFragmentManager(), "RateAppDialog");
    }

    void showPickImportPhotoType() {
        PickCameraGalleryDialog df = new PickCameraGalleryDialog();
        // df.setArguments(args);
        df.show(getFragmentManager(), "RateAppDialog");
    }


    void showCerrarSesion() {
        LogoutDialog df = new LogoutDialog();
        // df.setArguments(args);
        df.show(getFragmentManager(), "LogoutDialog");
    }

    public boolean refreshViewToNoInternet() {
        boolean refreshView = false;
        if (!Helper.isConnectedToInternet(getContext())) {
            refreshView = true;
        }
        return refreshView;
    }


    void refreshViews() {
        llEditarPerfil.setVisibility(View.GONE);
        llPreferencias.setVisibility(View.GONE);
        llValoraApp.setVisibility(View.GONE);
        lineOne.setVisibility(View.GONE);
        lineTwo.setVisibility(View.GONE);
        lineThree.setVisibility(View.GONE);
    }


    @Override
    public void onErrorImportPhoto(String var1) {
    }




    @Override
    public void onResume() {
        super.onResume();

        if (refreshViewToNoInternet()) {
            refreshViews();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Uri mUri = data.getData();
        //-------------------

        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(mUri, filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();

        Bitmap loadedBitmap = BitmapFactory.decodeFile(picturePath);

        ExifInterface exif = null;
        try {
            File pictureFile = new File(picturePath);
            exif = new ExifInterface(pictureFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        int orientation = ExifInterface.ORIENTATION_NORMAL;

        if (exif != null)
            orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                loadedBitmap = rotateImage(loadedBitmap, 90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                loadedBitmap = rotateImage(loadedBitmap, 180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                loadedBitmap = rotateImage(loadedBitmap, 270);
                break;
        }

        Bitmap bmpToUploadStorage = scaleBitmap(loadedBitmap, 300, 300);
        ivUserImage.setImageBitmap(bmpToUploadStorage);

        //envia a storage firebase
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        loadedBitmap.compress(Bitmap.CompressFormat.JPEG, 25, baos);
        pictureData = baos.toByteArray();

    }

    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

    private Bitmap scaleBitmap(Bitmap bm, int maxWidth, int maxHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();

        Log.v("Pictures", "Width and height are " + width + "--" + height);

        if (width > height) {
            // landscape
            float ratio = (float) width / maxWidth;
            width = maxWidth;
            height = (int) (height / ratio);
        } else if (height > width) {
            // portrait
            float ratio = (float) height / maxHeight;
            height = maxHeight;
            width = (int) (width / ratio);
        } else {
            // square
            height = maxHeight;
            width = maxWidth;
        }

        Log.v("Pictures", "after scaling Width and height are " + width + "--" + height);

        bm = Bitmap.createScaledBitmap(bm, width, height, true);
        return bm;
    }


    void setUserInfo() {
        UserPreference userPreference = Helper.getUserAppPreference(getContext());
        token = userPreference.getToken();
        if (userPreference.isLogged()) {
            tvUserName.setText(userPreference.getName());

            if (userPreference.getRegisterLoginType().equals(Constants.REGISTER_TYPES.EMAIL)) {
                if (userPreference.getImage().equals("")) {
                    ivUserImage.setImageResource(R.drawable.ic_camera_account);
                } else {
                    Helper.urlToImageView(userPreference.getImage(), ivUserImage, getContext());
                    Helper.urlToImageView(userPreference.getImage(), ivBackgroundUserImage, getContext());

                }
            } else {
                if (mifoto == null) {
                    Helper.urlToImageView(userPreference.getImage(), ivUserImage, getContext());
                    Helper.urlToImageView(userPreference.getImage(), ivBackgroundUserImage, getContext());
                } else {
                    //  ivUserImage.setBackground(mifoto);
                    ivUserImage.setImageResource(mifoto.getGenerationId());
                }
            }


        } else {
            tvUserName.setVisibility(View.GONE);

            // GoAseconds(null);
        }
    }


    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getContext())
                        .setSmallIcon(R.drawable.agenda)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");

        Intent notificationIntent = new Intent(getContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getContext(), 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    @Override
    public void temporalUserRegistered(String idTempUser) {

    }

    @Override
    public void tokenGenerated(String token) {

    }

    @Override
    public void userRegistered(Usuario usuario) {

    }

    @Override
    public void loginSuccess(Usuario usuario) {

    }

    @Override
    public void loginSocialMediaSuccess(Usuario usuario) {

    }

    @Override
    public void forgotPasswordSuccess(String message) {

    }

    @Override
    public void reSendCodeSuccess(String message) {

    }

    @Override
    public void userGot(Usuario usuario) {

    }

    @Override
    public void validateCodeSuccess(Usuario usuario) {

    }

    @Override
    public void routesByInterestSuccess(List<String> idRoutes) {

    }

    @Override
    public void userUpdated(Usuario usuario) {

    }

    @Override
    public void versionApp(String version) {

    }

    @Override
    public void imageUploaded(String imageUrl) {

        UserPreference userPreference= Helper.getUserAppPreference(getContext());
        userPreference.setImage(imageUrl);;
        Helper.saveUserAppPreference(getContext(),userPreference);

        next(MainActivity.class,getContext(),null);
        Toast.makeText(getContext(), "Imagen Actualizada", Toast.LENGTH_LONG).show();

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
    public void onPause() {
        super.onPause();

        String hola="";
     //   sendCallback();
    }

    void sendCallback() {

        Activity ahhh = getActivity();

        if (ahhh instanceof fragmentVisibleAccount) {
            ((fragmentVisibleAccount) ahhh).onFragmentVisibleAccount(Constants.FRAGMENTS_TABS.ACCOUNT);
        }

    }

    public interface fragmentVisibleAccount {
        void onFragmentVisibleAccount(int fragmentViewing);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        String hola="";
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            MainActivity.FRAGMENT_VIEWING=Constants.FRAGMENTS_TABS.ACCOUNT;
        }
    }
}
