package com.example.adelfo.miscontactos;

//import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
//import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import static android.content.ContentValues.TAG;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.view.Gravity;

/**
 * Created by Adelfo on 04/12/2016.
 */

public class NotificationService extends FirebaseMessagingService{

    ActionBar actionBar;
    public static final int NOTIFICATION_ID_1 = 001;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //Notificacion 1
        Intent i = new Intent();
        i.setAction("VER_PERFIL");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent i2 = new Intent();
        i2.setAction("FOLLOW_UNFOLLOW");
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(this, 0, i2, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent i3 = new Intent();
        i3.setAction("VER_USUARIO");
        PendingIntent pendingIntent3 = PendingIntent.getBroadcast(this, 0, i3, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_dog_house, getString(R.string.texto_ver_mi_perfil), pendingIntent).build();

        NotificationCompat.Action action2 =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_dog_leash, getString(R.string.texto_follow_unfollow), pendingIntent2).build();

        NotificationCompat.Action action3 =
                new NotificationCompat.Action.Builder(R.drawable.year_of_dog_50, getString(R.string.texto_ver_usuario), pendingIntent3).build();


        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender()
                .setHintHideIcon(true)
                .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.grass))
                .setGravity(Gravity.CENTER_VERTICAL);

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.year_of_dog_50)
                .setContentTitle("Notificacion")
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .extend(wearableExtender.addAction(action))
                .extend(wearableExtender.addAction(action2))
                .extend(wearableExtender.addAction(action3));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID_1, notificacion.build());
    }
}
