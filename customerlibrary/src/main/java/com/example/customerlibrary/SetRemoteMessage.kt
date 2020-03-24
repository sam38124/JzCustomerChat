package com.example.customerlibrary

import android.app.ActivityManager
import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import com.google.firebase.messaging.RemoteMessage
import com.orange.EventBus.updateMessage
import org.greenrobot.eventbus.EventBus

class SetRemoteMessage {
    companion object{
        fun pushMessage(remoteMessage: RemoteMessage,context:Context){
            val admin = remoteMessage.data["type"]
            val post = remoteMessage.data["data"]
            val time = remoteMessage.data["time"]
            val dbname = remoteMessage.from.toString().replace("/topics/", "")
            Log.i("MyFirebaseService", "收到" + dbname)
            Log.i("MyFirebaseService", "admin " + admin)
            Log.i("MyFirebaseService", "post " + post)
            Log.i("MyFirebaseService", "time " + time)
            Log.i("MyFirebaseService", "app " +context)
            if(admin!=null){
                    if(appOnForeground(context)){
                        EventBus.getDefault().post(updateMessage(admin))
                    }else{
//                    AddAdvice(admin.toString(), UnicodeUtil.unicodeToString(post))
                    }
            }
        }
        private fun appOnForeground(context: Context): Boolean {
            var activityManager =  context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager;
            val appProcesses: List<ActivityManager.RunningAppProcessInfo> = activityManager.getRunningAppProcesses()
                ?: return false
            for (appProcess in appProcesses) {
                if (appProcess.processName.equals(context.packageName) && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return true
                }
            }
            return false
        }
    }
}