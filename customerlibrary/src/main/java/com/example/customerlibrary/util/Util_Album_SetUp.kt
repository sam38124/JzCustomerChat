package com.example.customerlibrary.util

import androidx.fragment.app.Fragment
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.internal.entity.CaptureStrategy

class Util_Album_SetUp{
    fun SetMatisse(con:Fragment){
        Matisse.from(con)
            .choose(MimeType.ofImage())//图片类型
            .countable(false)//true:选中后显示数字;false:选中后显示对号
            .maxSelectable(1)
            .showSingleMediaType(true)
            .capture(false)//选择照片时，是否显示拍照
            .imageEngine(Util_Glide_Egine())
            .captureStrategy(
                CaptureStrategy(
                    true,
                    "com.example.xx.fileprovider"
                )
            )//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
            .forResult(1)//
    }
}