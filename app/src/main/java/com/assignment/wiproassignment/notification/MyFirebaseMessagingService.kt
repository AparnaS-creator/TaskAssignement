package com.apotheka.patient.notification

/**
 * Created by Swapnil Nandapure
 */

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.apotheka.patient.R
import com.apotheka.patient.ui.SplashActivity
import com.apotheka.patient.utill.AppInstance
import com.apotheka.patient.utill.appIsInBackground
import com.apotheka.patient.utill.getUserObject
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*


class MyFirebaseMessagingService : FirebaseMessagingService() {

    /**
     * Called when message is received.
     * https://github.com/firebase/quickstart-android
     * https://android.jlelse.eu/android-push-notification-using-firebase-and-advanced-rest-client-3858daff2f50
     * http://pushtry.com/
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        try {
            // TODO(developer): Handle FCM messages here.
            // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
            Log.e(TAG, "From: ${remoteMessage?.from}")

            /*String data = remoteMessage.getData().get("data");*/
            val data = remoteMessage.data
            System.out.println("Message Received====> $remoteMessage")

            val text = data["data"]
            val title = Objects.requireNonNull(remoteMessage.notification)!!.title
            val body = remoteMessage.notification!!.body
            System.out.println("Message Received Text ====> $text")
            System.out.println("Message Received Title ====> $title")
            System.out.println("Message Received Body ====> $body")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        /*try {
            // Check if message contains a data payload.
            remoteMessage?.data?.isNotEmpty()?.let {
                Log.e(TAG, "Message data payload: " + remoteMessage.data)
                try {
                    if (remoteMessage.data != null) {
                        for (entry in remoteMessage.data.entries) {
                            val key = entry.key
                            val value = entry.value
                            Log.e(TAG, "key, $key value $value")
                        }
                    }
                    //val title = remoteMessage.data["message"].toString()
                    //sendNotification(title)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }*/

        try {
            // Check if message contains a notification payload.
            remoteMessage?.notification?.let {
                Log.e(TAG, "Message Notification Body: ${it.body}")
                try {
                    //sendNotification(Objects.requireNonNull(remoteMessage!!.notification!!.title!!))
                    val title: String = if (it.title!!.isEmpty()) {
                        getString(R.string.app_name)
                    } else {
                        it.title!!
                    }
                    val message: String = if (it.body!!.isEmpty()) {
                        getString(R.string.app_name)
                    } else {
                        it.body!!
                    }

                    //check app instance
                    AppInstance.userDataObj = getUserObject(this)
                    val userId = AppInstance.userDataObj!!.id!!
                    if(!userId.isNullOrEmpty()) {
                        sendNotification(title, message)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        //sendNotification(Objects.requireNonNull(remoteMessage.getNotification()).getTitle(), remoteMessage.getNotification().getBody(), text);


    }
    // [END receive_message]

    // [START on_new_token]
    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String) {
        Log.e(TAG, "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token)
    }
    // [END on_new_token]

    /**
     * Schedule async work using WorkManager.
     */
    /*private fun scheduleJob() {
        // [START dispatch_job]
        val work = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
        WorkManager.getInstance().beginWith(work).enqueue()
        // [END dispatch_job]
    }*/

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private fun handleNow() {
        Log.e(TAG, "Short lived task is done.")
    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param title FCM message title received.
     * @param messageBody FCM message body received.
     */
    private fun sendNotification(title: String, messageBody: String) {
        try {

            //check is call ended by other user

            //unique notification id and get current step
            val notificationID = Random().nextInt(50 - 1 + 1) + 1
            Log.e(TAG, "sendNotification()")

            //check app is in background or not
            val pendingIntent: PendingIntent
            pendingIntent = if (appIsInBackground(this)) {
                val intent = Intent(this, SplashActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                PendingIntent.getActivity(this, notificationID, intent,
                        PendingIntent.FLAG_ONE_SHOT)
            } else {
                NotificationActivity.getDismissIntent(notificationID, this)
            }

            /*val intent = Intent(this, Splash::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            val pendingIntent = PendingIntent.getActivity(this, notificationID *//* Request code *//*, intent,
                    PendingIntent.FLAG_ONE_SHOT)*/

            val channelId = getString(R.string.default_notification_channel_id)
            val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val notificationBuilder = NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(title)
                    .setContentText(messageBody)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Since android Oreo notification channel is needed.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId,
                        getString(R.string.app_name),
                        NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(channel)
            }

            //build notification
            notificationManager.notify(notificationID /* ID of notification */, notificationBuilder.build())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * static objects
     */
    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }


}
