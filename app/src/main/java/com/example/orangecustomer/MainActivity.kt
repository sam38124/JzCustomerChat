package com.example.orangecustomer

import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.Fragment
import com.example.customerlibrary.AdminBeans
import com.example.customerlibrary.ChatPage
import com.orange.jzchi.jzframework.JzActivity

class MainActivity : JzActivity() {
    override fun changePageListener(tag: String, frag: Fragment) {

    }

    override fun keyEventListener(event: KeyEvent): Boolean {
        return false
    }

    override fun savedInstanceAble(): Boolean {
    return false
    }

    override fun viewInit(rootview: View) {
        AdminBeans.setMessageInstance("sam38125","0")
        getControlInstance().setHome(ChatPage("sam38124","","192.168.3.136"),"ChatPage")
    }


}
