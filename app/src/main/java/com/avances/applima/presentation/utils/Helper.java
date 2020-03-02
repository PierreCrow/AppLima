package com.avances.applima.presentation.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;

import com.avances.applima.R;
import com.avances.applima.domain.model.UserPreference;
import com.avances.applima.presentation.ui.activities.MainActivity;
import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Helper {



        public static int calculateNoOfColumns(Context context, float columnWidthDp) { // For example columnWidthdp=180
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
            int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
            return noOfColumns;
        }


    public static void addNotification(Context ctx) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Sets an ID for the notification, so it can be updated.
            int notifyID = 1;
            String CHANNEL_ID = "my_channel_01";// The id of the channel.
            CharSequence name = "ChanelName";// The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            // Create a notification and set the notification channel.
            Notification notification = new Notification.Builder(ctx)
                    .setContentTitle("New Message")
                    .setContentText("You've received new messages.")
                    .setSmallIcon(R.mipmap.iconolima)
                    .setChannelId(CHANNEL_ID)
                    .build();

            NotificationManager mNotificationManager =
                    (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(mChannel);
        }
        else
        {
            addNot(ctx);
        }


    }


    public static void addNot(Context ctx)
    {
        NotificationCompat.Builder mBuilder =   new NotificationCompat.Builder(ctx)
                .setSmallIcon(R.mipmap.iconolima) // notification icon
                .setContentTitle("Notification!") // title for notification
                .setContentText("Hello word") // message for notification
                .setAutoCancel(true); // clear notification after click
    //    Intent intent = new Intent(ctx, MainActivity.class);
     //     PendingIntent pi = PendingIntent.getActivity(ctx,0,intent,Intent.FLAG_ACTIVITY_NEW_TASK);
     //   mBuilder.setContentIntent(pi);
        NotificationManager mNotificationManager =
                (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
    }

    public String getAppVersion(Context ctx) {
        String appVersion = "";
        try {
            PackageInfo pInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            appVersion = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appVersion;
    }

    public static int compareVersions(String appVersion, String minimumVersion) {
        int response = Integer.parseInt(Constants.APP_VERSION.EQUAL);

        String[] oldNumbers = appVersion.split("\\.");
        String[] newNumbers = minimumVersion.split("\\.");

        int maxIndex = Math.min(oldNumbers.length, newNumbers.length);

        for (int i = 0; i < maxIndex; i++) {
            int oldVersionPart = Integer.valueOf(oldNumbers[i].trim());
            int newVersionPart = Integer.valueOf(newNumbers[i].trim());

            if (oldVersionPart < newVersionPart) {
                response = Integer.parseInt(Constants.APP_VERSION.MINOR);
                break;
            } else if (oldVersionPart > newVersionPart) {
                response = Integer.parseInt(Constants.APP_VERSION.MAJOR);
                break;
            }
        }

        if (response == Integer.parseInt(Constants.APP_VERSION.EQUAL) && oldNumbers.length != newNumbers.length) {
            response = (oldNumbers.length > newNumbers.length) ? 1 : -1;
        }

        return response;
    }



    protected void showDialogConfirmationNoCancelableTxtConfirmm(Context context, String title, String question, String txtConfirm, int type, final ConfirmationDialogCallback callback) {
        final DialogUI dialogUI = new DialogUI(context);
        dialogUI.showDialogConfirmationNoCancelableTxtConfirm(title, question, txtConfirm, type, callback);
    }

    public static boolean getVersionUpdated(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("VersionPreference", Context.MODE_PRIVATE);
        return preferences.getBoolean("versionUpdated", true);
    }

    public static void saveVersionUpdated(Context context, boolean b) {
        SharedPreferences preferences = context.getSharedPreferences("VersionPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("versionUpdated", b);
        editor.apply();
    }

    private void goToLink(String url,Context ctx) {
        Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        ctx.startActivity(browse);
    }

    public static void showBottomSourcePhoto(int requestCode, FragmentManager fragmentManager) {
        ImportPhotoBottomFragment bottomSheetFragment = new ImportPhotoBottomFragment();
        // Bundle args = new Bundle();
        // args.putInt(ImportPhotoBottomFragment.EXTRA_PARAMETER_DATA_TAG, requestCode);
        // bottomSheetFragment.setArguments(args);
        bottomSheetFragment.show(fragmentManager, bottomSheetFragment.getTag());
    }


    public static boolean isEmailValid(String email) {
        return !(email == null || TextUtils.isEmpty(email)) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static void addCalendarEvent(Context context, String tittle, String description, String startDateEvent, String finalDateEvent) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        Date startDate = null;
        Date finalDate = null;
        try {
            startDate = format.parse(startDateEvent);
            finalDate = format.parse(finalDateEvent);
        } catch (ParseException e) {
            e.printStackTrace();
        }



        long calID = 1;
        long startMillis = 0;
        long endMillis = 0;
        Calendar beginTime = Calendar.getInstance();
        beginTime.setTime(startDate);
        //beginTime.set(2020, 2, 4, 7, 30);
        startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.setTime(finalDate);
       // endTime.set(2020, 2, 4, 15, 00);
        endMillis = endTime.getTimeInMillis();

        ContentResolver cr = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, startMillis);
        values.put(CalendarContract.Events.DTEND, endMillis);
        values.put(CalendarContract.Events.TITLE, tittle);
        values.put(CalendarContract.Events.DESCRIPTION, description);
        values.put(CalendarContract.Events.CALENDAR_ID, calID);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "America/New_York");

        try {
            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
        } catch (Exception e) {
            String jdshjds=e.getMessage();
        }


    }

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Boolean conectado = null;
        if (connectivity != null) {
            NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting())
                conectado = true;
            else {
                conectado = false;
            }
        } else {
            conectado = false;
        }
        return conectado;
    }



    public static void saveUserAppPreference(Context context, UserPreference userPreference) {

        SharedPreferences preferenciasssee = context.getSharedPreferences(Constants.PREFERENCES.PREFERENCE_CURRENT_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editoriieei = preferenciasssee.edit();
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_ID, userPreference.getId());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_NAME, userPreference.getName());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_LAST_NAME, userPreference.getLastName());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_EMAIL, userPreference.getEmail());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_PASSWORD, userPreference.getPass());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_PHONE, userPreference.getPhone());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_COUNTRY, userPreference.getCountry());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_IMAGE, userPreference.getImage());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_LOGIN_TYPE, userPreference.getRegisterLoginType());
        editoriieei.putBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_SECONDS_VIEWED, userPreference.isSecondsToOfferViewed());
        editoriieei.putBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_LOGGED, userPreference.isLogged());
        editoriieei.putBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_HAS_INTERESTS, userPreference.isHasInterests());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_INTEREST_1, userPreference.getInterest_1());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_INTEREST_2, userPreference.getInterest_2());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_INTEREST_3, userPreference.getInterest_3());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_INTEREST_4, userPreference.getInterest_4());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_INTEREST_5, userPreference.getInterest_5());
        editoriieei.putBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_FIRST_SYNC_SUCCESS, userPreference.isFirstSyncSuccess());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_LAT, userPreference.getLat());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_LNG, userPreference.getLng());
        editoriieei.putBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_HAS_LOCATION, userPreference.isHasLocation());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_TOKEN_FCM, userPreference.getTokenFCM());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_ID_TEMPORAL, userPreference.getIdTemporal());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_ADDRESS, userPreference.getAddress());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_GENDER, userPreference.getGender());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_BIRTH_DATE, userPreference.getBirthDate());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_TOKEN, userPreference.getToken());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_PERMANENCY_DAYS, userPreference.getPermanencyDays());
        editoriieei.putBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_LAST_VERSION, userPreference.isLastVersion());
        editoriieei.apply();
    }

    public static UserPreference getUserAppPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.PREFERENCES.PREFERENCE_CURRENT_USER, Context.MODE_PRIVATE);
        UserPreference userPreference =
                new UserPreference(preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_ID, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_NAME, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_LAST_NAME, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_EMAIL, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_PASSWORD, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_PHONE, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_COUNTRY, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_IMAGE, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_LOGIN_TYPE, Constants.REGISTER_TYPES.NOT_LOGIN),
                        preferences.getBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_LOGGED, false),
                        preferences.getBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_SECONDS_VIEWED, false),
                        preferences.getBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_HAS_INTERESTS, false),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_INTEREST_1, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_INTEREST_2, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_INTEREST_3, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_INTEREST_4, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_INTEREST_5, ""),
                        preferences.getBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_FIRST_SYNC_SUCCESS, false),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_LAT, "0.0"),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_LNG, "0.0"),
                        preferences.getBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_HAS_LOCATION, false),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_TOKEN_FCM, "Wc545fhgbd444Upo93bjdjh9845frrrrrrffghfff"),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_ID_TEMPORAL, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_ADDRESS, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_GENDER, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_BIRTH_DATE, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_TOKEN, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_PERMANENCY_DAYS, ""),
                        preferences.getBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_LAST_VERSION, false));

        return userPreference;
    }

    public static void loadDistritFromHome(Context context)
    {
        SharedPreferences preferenciasssee = context.getSharedPreferences("PlaceDistritView", Context.MODE_PRIVATE);
        SharedPreferences.Editor editoriieei = preferenciasssee.edit();
        editoriieei.putBoolean("FromDistritDetail", false);
        // editoriieei.putString("idPlace", place.getId());
        editoriieei.apply();
    }

    public static String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }

    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }


    public static String convertTwoDecimals(float number)
    {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        String converted = Double.toString(bd.doubleValue());
        return converted;
    }

    public static void urlToImageView(String urlFoto, ImageView imagev, Context contexto) {

        Glide.with(contexto)
                .load(urlFoto)
                //.override(100, 200)
                .fitCenter()
                .into(imagev);

    }


    public static void logOut(Context context, int typeLogin) {
        UserPreference userPreference = Helper.getUserAppPreference(context);
        userPreference.setLogged(false);
        userPreference.setRegisterLoginType(Constants.REGISTER_TYPES.NOT_LOGIN);
        Helper.saveUserAppPreference(context, userPreference);

        if (typeLogin == Constants.LOGIN_TYPES.FACEBOOK) {
            LoginManager.getInstance().logOut();

        }

        if (typeLogin == Constants.LOGIN_TYPES.GOOGLE) {
            GoogleApiClient mGoogleApiClient;

            mGoogleApiClient = new GoogleApiClient.Builder(context) //Use app context to prevent leaks using activity
                    //.enableAutoManage(this /* FragmentActivity */, connectionFailedListener)
                    .addApi(Auth.GOOGLE_SIGN_IN_API)
                    .build();


            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            // ...
                            //    Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_SHORT).show();
                            //   Intent i=new Intent(getApplicationContext(),MainActivity.class);
                            //  startActivity(i);
                        }
                    });
        }

    }


    public static void hideKeyboard(Activity activity) {//activity.class
 /*       InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);*/
        //   activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public static boolean gpsIsEnabled(Context context) {
        final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return false;
        } else {
            return true;
        }
    }


    public static String getKeyHasgh(Context ctx) {
        PackageInfo info;
        String keyHash="";
        try {
            info = ctx.getPackageManager().getPackageInfo("com.avances.applima", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                keyHash = something;
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }


        keyHash = keyHash + "   " + keyHash;
        return keyHash;
    }


    void comprobarEstadoInicioSesionfacebook() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

      //  LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }


}
