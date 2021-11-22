package com.neppplus.colosseum_20211117

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neppplus.colosseum_20211117.databinding.ActivityViewReplyDetailBinding
import com.neppplus.colosseum_20211117.datas.ReplyData
import com.neppplus.colosseum_20211117.utils.ServerUtil
import org.json.JSONObject

class ViewReplyDetailActivity : BaseActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding : ActivityViewReplyDetailBinding
        lateinit var mReplyData: ReplyData

        val mReReplyList = ArrayList

                super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_reply_detail)


        setValues()
        setupEvents()
    }


    override fun setupEvents() {
    }

    override fun setValues() {

        mReplyData = intent.getSerializableExtra("reply") as ReplyData

        binding.txtWriterNickName.text = mReplyData.writer.nikname

        binding.txtContent.text = mReplyData.content

        getReplydetailFromServer()

        mReReplyAdapter = ReReplyAdapter(mContext, R.layout.re_reply_list_item,mReReplyadapter)
    }

    fun getReplydetailFromServer() {

        ServerUtil.getRequestReplyDetail(mContext, mReplyData.id, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

                val dataObj = jsonObj.getJSONObject("data")
                val replyObj = dataObj.getJSONObject("reply")
                val repliesArr = replyObj.getJSONArray("replies")

                for(i in 0 until repliesArr.length()){

//                    [] => { }(JSONObject) 추출 -> ReplData로 변환  -> 대댓글 목록에 추가(최종목표)
                    mReReplyList.add( ReplyData.getReplyDataFromJson((repliesArr.getJSONObject(i))))

                }

                runOnUiThread {
                    mReReplyAdatper.notifyDataSetChanged()
                }
            }


        })
    }
}