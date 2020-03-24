package com.example.customerlibrary.callback

import android.app.Dialog
import android.util.Log
import android.view.KeyEvent
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.customerlibrary.AdminBeans
import com.example.customerlibrary.R
import com.orange.jzchi.jzframework.JzActivity
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket

abstract class Socket_C(val function: Int) {
    companion object {
        var counter = 0
        var progress: Dialog? = null
        var error: Dialog? = null
        var ip=""
    }
    var act=JzActivity.getControlInstance().getRootActivity()
    lateinit var s: Socket
    lateinit var br: DataInputStream
    lateinit var ps: DataOutputStream
    fun writeUTF(a: String) {
        ps.writeUTF(a)
    }

    fun writeInt(a: Int) {
        ps.writeInt(a)
    }

    fun readUTF(): String {
        return br.readUTF();
    }

    fun readInt(): Int {
        return br.readInt();
    }

    //開始請求
    fun start() {
        try {
            Log.e("Socket", "請求開始" + function)
            ShowProgress(true)
            counter++
            s = Socket(ip, 10001)
            br = DataInputStream(s.getInputStream())
            ps = DataOutputStream(s.getOutputStream())
            s.soTimeout = 10000
            ps.writeUTF(AdminBeans.admin)
            ps.writeUTF(AdminBeans.password)
            ps.writeInt(function)
            init()
            stop()
        } catch (e: Exception) {
            e.printStackTrace()
            counter--
            error()
        }
    }

    open fun error() {
        act.handler.post {
            ShowProgress(false)
            if (error == null) {
                error = object : Dialog(act, R.style.MyDialog) {
                    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
                        return false
                    }
                }
                error!!.setContentView(R.layout.error)
                error!!.getWindow()!!.setLayout(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
                )
                error!!.setCancelable(false)
                error!!.setCanceledOnTouchOutside(false)
                error!!.show()
                error!!.findViewById<TextView>(R.id.retry).setOnClickListener {
                    error!!.dismiss()
                    Thread { start() }.start()
                }
                error!!.findViewById<TextView>(R.id.cancel).setOnClickListener {
                    error!!.dismiss()
                }
            } else {
                if (!error!!.isShowing) {
                    error!!.show()
                    error!!.findViewById<TextView>(R.id.retry).setOnClickListener {
                        error!!.dismiss()
                        Thread { start() }.start()
                    }
                    error!!.findViewById<TextView>(R.id.cancel).setOnClickListener {
                        error!!.dismiss()
                    }
                }
            }
        }
    }

    //請求完畢
    fun stop() {
        counter--
        Log.e("Socket", "請求完畢${function}/" + counter)
        act.handler.postDelayed({
            if (counter <= 0) {
                ShowProgress(false)
            }
        },500)
    }

    //代碼處理
    abstract fun init()


    fun ShowProgress(show: Boolean) {
        if ((function == 111&&act.FragName=="Frag_Logo")||function==117||function==135) {
            return
        }
        if (show) {
            act.handler.post {
                if (progress == null) {
                    progress = Dialog(act, R.style.MyDialog)
                    progress!!.setContentView(R.layout.progress)
                    progress!!.getWindow()!!.setLayout(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT
                    )
                    progress!!.setCancelable(false)
                    progress!!.setCanceledOnTouchOutside(false)
                    progress!!.show()
                } else {
                    if (!progress!!.isShowing) {
                        progress!!.show()
                    }
                }
            }
        } else {
            act.handler.post {
                if (progress != null) {
                    progress!!.dismiss()
                }
            }
        }
    }
}