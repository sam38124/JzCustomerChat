package com.example.customerlibrary

import com.google.firebase.messaging.FirebaseMessaging
import javax.security.auth.callback.Callback

class AdminBeans {
    companion object {
        interface result{
            fun result(a:Boolean)
        }
        fun setMessageInstance(admin:String,password:String,callback: result){
            this.admin=admin
            this.password=password
            FirebaseMessaging.getInstance().subscribeToTopic("").addOnCompleteListener {
                callback.result(true)
            }.addOnFailureListener {
                callback.result(false)
            }
        }
        var admin=""
        var password=""
    }
}