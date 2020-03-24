package com.example.customerlibrary

import android.os.Handler
import com.example.customerlibrary.beans.Messageitem
import com.example.customerlibrary.callback.AddLove_C
import com.example.customerlibrary.callback.GetPost_C
import com.example.customerlibrary.callback.Socket_C
import com.orange.jzchi.jzframework.tool.UnicodeUtil
import java.lang.Thread.sleep

class Command {
    var handler= Handler()
    fun GetMail(take: String, it: Messageitem, ad: String, caller: GetPost_C) {
        Thread{
            val originsize = it.admin.size
            try {
                object : Socket_C(114){
                    override fun init() {
                        writeUTF(ad)
                        writeUTF(take)
                        var a = readUTF()
                        while (a != "完畢") {
                            it.add2(a, readUTF(), readUTF(), readUTF(), readUTF(), readUTF(), readUTF())
                            a = readUTF()
                        }
                        if (originsize == it.admin.size) {
                            it.button = true
                        }
                        it.success = true
                        handler.post { caller.Result(true) }
                    }}.start()
            } catch (e: Exception) {
                sleep(1000)
                e.printStackTrace()
                it.success = false
                handler.post { caller.Result(false) }
            }
        }.start()

    }

    fun SendMail(ad: String, file: String, Message: String,caller:GetPost_C) {
        Thread{
            try {
                object :Socket_C(116){
                    override fun init() {
                        writeUTF(ad)
                        writeUTF(file)
                        writeUTF(UnicodeUtil.stringToUnicode(Message))
                        var a=readInt() == 1
                        handler.post { caller.Result(a) }
                    }}.start()
            } catch (e: Exception) {
                sleep(1000)
                e.printStackTrace()
                handler.post { caller.Result(false) }
            }
        }.start()
    }

    fun refreshMail(take: String, it: Messageitem, ad: String,caller:GetPost_C) {
        Thread{
            val originsize = it.admin.size
            try {
                object :Socket_C(135){
                    override fun init() {
                        writeUTF(ad)
                        writeUTF(take)
                        var a = readUTF()
                        while (a != "完畢") {
                            it.insert(a, readUTF(), readUTF(), readUTF(), readUTF(), readUTF(), readUTF())
                            a = readUTF()
                        }
                        if (originsize == it.admin.size) {
                            it.button = true
                        }
                        it.success = true
                        handler.post { caller.Result(true) }
                    }

                    override fun error() {
                        caller.Result(false)
                    }
                }.start()
            } catch (e: Exception) {
                sleep(1000)
                e.printStackTrace()
                it.success = false
                handler.post { caller.Result(false) }
            }
        }.start()
    }

    fun havemessage(caller: AddLove_C) {
        Thread{
            try {
                object :Socket_C(117){
                    override fun init() {
                        var a=readInt()
                        handler.post { caller.result(a) }
                    }

                    override fun error() {
                        caller.result(-1)
                    }
                }.start()
            } catch (e: Exception) {
                sleep(1000)
                e.printStackTrace()
                handler.post {  caller.result(-1)}

            }
        }.start()
    }
}