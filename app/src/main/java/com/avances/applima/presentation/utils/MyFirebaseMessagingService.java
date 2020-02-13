package com.avances.applima.presentation.utils;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.core.app.NotificationCompat;

import com.avances.applima.domain.model.UserPreference;
import com.avances.applima.presentation.ui.activities.MainActivity;
import com.avances.applima.presentation.ui.activities.Splash;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    String cuerpo, titulo, mensajito, mensjito2;

    static Integer idNotificacion = 1;


    private static final String TAG = "FCM Service";


    public interface GoSplash
    {
        public void onClose(String token);
    }

    private void sendTokenToServer(String idToken) {



    /*    UserPreference userPreference = Helper.getUserAppPreference(getApplicationContext());
        userPreference.setTokenFCM(idToken);
        Helper.saveUserAppPreference(getApplicationContext(),userPreference);
*/

        Activity ahhh= new Splash();

        if(ahhh instanceof GoSplash)
        {  ((GoSplash)ahhh).onClose(idToken);}

  /*
        //enviar token a tu app server
        SharedPreferences preferencias=getSharedPreferences("TokenDeMierda", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("token_preferente", idToken);
        editor.commit();*/
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        String tokenn = s;
    //    sendTokenToServer(tokenn);
        // Log.e("NEW_TOKEN",s);
    }


    //aca es cuando se manda mensaje desde la consola firebase
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.

        cuerpo = remoteMessage.getNotification().getBody().toString();
        titulo = remoteMessage.getNotification().getTitle().toString();

        if (titulo == null) {
            titulo = "Pata de Perro";
        }

        addNotification();

    }


    //aca es cuando se manda la notificacion desde el servicio web (con titulo)
   /* @Override
    public void handleIntent(Intent intent) {
        //super.handleIntent(intent);

        Bundle mBundle = intent.getExtras();
        titulo = mBundle.getString("gcm.notification.title");
        cuerpo = mBundle.getString("gcm.notification.body");

        if(titulo==null)
        {
            titulo="Pata de Perro";
        }

        try {
            addNotification();
        }
        catch (Exception ex)
        {mensajito=ex.getMessage();
        mensjito2=ex.getMessage();}


    }
*/
    private void addNotification() {

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        //.setSmallIcon(com.futuremobile.chongos.R.mipmap.pata_de_perro_android_noventa)
                        .setContentTitle(titulo)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentText(cuerpo);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Agregar la notificacion
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(idNotificacion++, builder.build());
        // idNotification++;
    }


}
