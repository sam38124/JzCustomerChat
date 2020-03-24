package com.example.customerlibrary

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.orange.EventBus.updateMessage
import com.orange.jzchi.jzframework.tool.UnicodeUtil
import org.greenrobot.eventbus.EventBus

class Server_Firebase : FirebaseMessagingService() {
    val channelid = "com.orange.tsport"
    var handler= Handler()
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.e("type", remoteMessage.from)



    }




    override fun onNewToken(s: String) {
        super.onNewToken(s)
        Log.i("Server_Firebase", "token $s")
    }
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_app_name)
            val descriptionText = getString(R.string.error)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelid, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    fun AddAdvice(title: String, content: String) {
        createNotificationChannel()
//        val pi = PendingIntent.getActivity(
//            this,
//            100,
//            Intent(this, MainPeace::class.java),
//            PendingIntent.FLAG_CANCEL_CURRENT
//        )
//        val builder = NotificationCompat.Builder(this, channelid)
//            .setSmallIcon(R.drawable.tsporticon)
//            .setContentTitle(title)
//            .setContentText(content)
//            .setStyle(
//                NotificationCompat.BigTextStyle()
//                    .bigText(content)
//            )
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)
//            .setContentIntent(pi)
//        with(NotificationManagerCompat.from(this)) {
//            // notificationId is a unique int for each notification that you must define
//            notify(10, builder.build())
//        }
    }
}