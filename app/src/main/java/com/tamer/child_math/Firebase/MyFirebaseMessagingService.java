package com.tamer.child_math.Firebase;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.tamer.child_math.Main2Activity;
import com.tamer.child_math.R;
import com.tamer.child_math.opertion;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Date;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {

            sendNotification(remoteMessage);


        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]

    /**
     * Schedule a job using FirebaseJobDispatcher.
     */


    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");


    }


    private void sendNotification(RemoteMessage remoteMessage) {
        int notificationId = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);



        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.cancel(notificationId);

        Intent resultIntent = null;

        Log.d(TAG, "sendNotification: "+remoteMessage.getData());
        PendingIntent dismissIntent = NotificationActivity.getDismissIntent(notificationId,remoteMessage.getData().get("referId"), this);

        if (remoteMessage.getData().containsKey("type")) {
            final int type = Integer.parseInt(remoteMessage.getData().get("type"));

            Log.d(TAG, "sendNotification: "+type);
            if (type==-1){

                final String referId = remoteMessage.getData().get("referId");
                 notificationId =referId.hashCode();

                 resultIntent = new Intent(this, opertion.class);;
                 resultIntent.putExtra("referId",referId);

            }else if (type==1){

                resultIntent= new Intent(this, opertion.class);
                resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(resultIntent);
                return;

            }else{

                resultIntent= new Intent(this, Main2Activity.class);
                resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
        }else {

            resultIntent= new Intent(this, Main2Activity.class);
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }

        PendingIntent  mainPendingIntent= PendingIntent.getActivity(getApplicationContext(), notificationId, resultIntent, PendingIntent.FLAG_ONE_SHOT);






        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.splash)
                .setContentTitle(remoteMessage.getMessageType())
                .setContentText(remoteMessage.getNotification().getBody())
                .setContentIntent(mainPendingIntent)
                .setVibrate(new long[]{1000, 1000})
                .setSound(defaultSoundUri)

                .setAutoCancel(false)

               ;

        if (remoteMessage.getData().containsKey("type")) {
            final int type = Integer.parseInt(remoteMessage.getData().get("type"));
            if (type==-1) {
                notificationBuilder .addAction(R.drawable.ic_accpet, "Accept", mainPendingIntent);
                notificationBuilder     .addAction(R.drawable.ic_cance, "Cancel", dismissIntent);
            }
        }

        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel("" + remoteMessage.getMessageId(), remoteMessage.getNotification().getBody(), NotificationManager.IMPORTANCE_DEFAULT);
            channel.setLockscreenVisibility(android.app.Notification.VISIBILITY_PUBLIC);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);

            channel.setShowBadge(true);
            notificationManager.createNotificationChannel(channel);

            notificationManager.notify(remoteMessage.getMessageId().hashCode(), notificationBuilder.setChannelId(channel.getId()).build());

        } else {
            notificationManager.notify(remoteMessage.getMessageId().hashCode(), notificationBuilder.build());

        }


    }

    @Override
    public void onNewToken(@NonNull String refreshedToken) {
        super.onNewToken(refreshedToken);

        FirebaseUtils.updateFCM(getApplicationContext(),refreshedToken,null);
    }
}