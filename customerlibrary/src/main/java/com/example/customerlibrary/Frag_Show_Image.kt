package com.example.customerlibrary

import android.graphics.drawable.Animatable
import android.util.Log
import android.view.KeyEvent
import com.facebook.common.logging.FLog
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.imagepipeline.image.ImageInfo
import com.orange.jzchi.jzframework.JzActivity
import com.orange.jzchi.jzframework.JzFragement
import kotlinx.android.synthetic.main.activity_show_image.view.*

class Frag_Show_Image(val url: String) : JzFragement(R.layout.activity_show_image) {
    override fun viewInit() {
        Log.e("class為",""+javaClass)
        rootview.title.text = url
         var controllerListener = object : BaseControllerListener<ImageInfo>() {
             override fun onFinalImageSet(id: String?, imageInfo: ImageInfo?, animatable: Animatable?) {
                if (imageInfo == null) {
                    return
                }
                val qualityInfo = imageInfo!!.getQualityInfo()
                FLog.d(
                    "Final image received! " + "Size %d x %d",
                    "Quality level %d, good enough: %s, full quality: %s",
                    imageInfo!!.getWidth(),
                    imageInfo!!.getHeight(),
                    qualityInfo.getQuality(),
                    qualityInfo.isOfGoodEnoughQuality(),
                    qualityInfo.isOfFullQuality()
                )
                 rootview.image.background=resources.getDrawable(R.color.white)
            }
            override fun onFailure(id: String, throwable: Throwable) {
                FLog.e(javaClass, throwable, "Error loading %s", id)
            }
        }
         var controller: DraweeController = Fresco.newDraweeControllerBuilder()
            .setControllerListener(controllerListener)
            .setUri(url)
            // other setters
            .build()
        rootview.image.controller=controller
        rootview.back.setOnClickListener {
            JzActivity.getControlInstance().goBack()
        }
    }
    override fun dispatchKeyEvent(event: KeyEvent) {
    }

}
