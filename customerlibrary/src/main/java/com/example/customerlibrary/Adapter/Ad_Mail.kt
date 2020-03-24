package com.example.customerlibrary.Adapter




import com.example.customerlibrary.ChatPage
import com.example.customerlibrary.R
import com.example.customerlibrary.beans.Messageitem
import com.example.customerlibrary.callback.Socket_C
import com.example.customerlibrary.util.Util_Tool.loadResPic
import com.orange.jzchi.jzframework.JzAdapter
import com.orange.jzchi.jzframework.JzActivity
import com.orange.jzchi.jzframework.tool.UnicodeUtil.unicodeToString
import kotlinx.android.synthetic.main.getalker_adapter.view.*


class Ad_Mail(private val a: Messageitem)
    : JzAdapter(R.layout.getalker_adapter) {
    override fun sizeInit(): Int {
        return a.admin.size
    }
    override fun onBindViewHolder(holder: JzAdapter.ViewHolder, position: Int) {
        holder.mView.pick.setText(a.pick[position])
        holder.mView.tit.text=unicodeToString((a.message[position]))
        if(a.reader[position].equals("1")){
            holder.mView.tit.setTextColor(holder.mView.context.resources.getColor(R.color.gray))
        }else{
            holder.mView.tit.setTextColor(holder.mView.context.resources.getColor(R.color.Black))
        }
        if(a.head.equals("nodata")){
            loadResPic(holder.mView.context,holder.mView.head,R.drawable.admin2)
        }else{
            holder.mView.head.setImageURI(a.head[position])
        }
        holder.mView.setOnClickListener {
            JzActivity.getControlInstance().changePage(ChatPage(a.admin[position], a.pick[position],Socket_C.ip),"Frag_Message",true)
        }
    }
}