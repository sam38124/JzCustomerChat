package com.example.customerlibrary.Adapter




import android.view.View
import com.example.customerlibrary.AdminBeans
import com.example.customerlibrary.Frag_Show_Image
import com.example.customerlibrary.R
import com.example.customerlibrary.beans.Messageitem
import com.example.customerlibrary.util.Util_Tool.CalculateTime
import com.example.customerlibrary.util.Util_Tool.loadResPic
import com.orange.jzchi.jzframework.JzAdapter
import com.orange.jzchi.jzframework.JzActivity
import com.orange.jzchi.jzframework.tool.UnicodeUtil.stringToUnicode
import com.orange.jzchi.jzframework.tool.UnicodeUtil.unicodeToString
import kotlinx.android.synthetic.main.in_message.view.*


class Ad_InMail(private val a: Messageitem)
    : JzAdapter(R.layout.in_message) {
    override fun sizeInit(): Int {
      return a.admin.size
    }
    override fun onBindViewHolder(holder: JzAdapter.ViewHolder, position: Int) {
        holder.mView.me.visibility=View.GONE
        holder.mView.you.visibility=View.GONE
        if(a.admin[position].equals(AdminBeans.admin)){
            holder.mView.me.visibility=View.VISIBLE
            if(a.file[position].equals("nodata")){holder.mView.meimage.visibility=View.GONE}else{holder.mView.meimage.visibility=View.VISIBLE
                holder.mView.imageme.setImageURI(a.file[position])
                holder.mView.imageme.setOnClickListener {
                    JzActivity.getControlInstance().changePage(Frag_Show_Image(a.file[position]),"Frag_Show_Image",true)
                  }
            }
            holder.mView.pome.text = unicodeToString(a.message[position])
            holder.mView.timeme.text=CalculateTime(a.time[position])
        }else{
            holder.mView.you.visibility=View.VISIBLE
            if(a.file[position].equals("nodata")){holder.mView.youimage.visibility=View.GONE}else{
                holder.mView.youimage.setOnClickListener {
                    JzActivity.getControlInstance().changePage(Frag_Show_Image(a.file[position]),"Frag_Show_Image",true)
                }
                holder.mView.youimage.visibility=View.VISIBLE
                holder.mView.image.setImageURI(a.file[position])}
            holder.mView.po.text=unicodeToString(a.message[position])
                if(a.head[position].equals("nodata")){loadResPic(holder.mView.context,holder.mView.head,R.drawable.admin2)}else{
                holder.mView.head.setImageURI(a.head[position])
            }
            holder.mView.name.text=a.pick[position]
            holder.mView.time.text=CalculateTime(a.time[position])
        }
    }
}