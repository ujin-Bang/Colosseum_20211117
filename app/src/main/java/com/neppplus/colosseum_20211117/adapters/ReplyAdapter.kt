package com.neppplus.colosseum_20211117.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.neppplus.colosseum_20211117.R
import com.neppplus.colosseum_20211117.datas.ReplyData
import com.neppplus.colosseum_20211117.datas.TopicData
import java.text.SimpleDateFormat

class ReplyAdapter(
    val mContext: Context,
    val resId: Int,
    val mList: List<ReplyData>) : ArrayAdapter<ReplyData>(mContext, resId, mList) {

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if (tempRow == null) {

            tempRow  =  mInflater.inflate(R.layout.reply_list_item, null)

        }

        val row = tempRow!!

        val data = mList[position]

        val txtReplyContent = row.findViewById<TextView>(R.id.txtReplyContent)
        val txtWriterNickname = row.findViewById<TextView>(R.id.txtWriterNickname)
        val txtSelectedSide = row.findViewById<TextView>(R.id.txtSelectedSide)
        val txtCreatedAt = row.findViewById<TextView>(R.id.txtCreatedAt)

        txtReplyContent.text = data.content

        txtWriterNickname.text = data.writer.nickname

        txtSelectedSide.text = "(${data.selectedSide.title})"

        val sdf = SimpleDateFormat("yyyy/MM/dd a h시 m분")

        txtCreatedAt.text = sdf.format(data.createdAt.time)

        return row

    }

}





