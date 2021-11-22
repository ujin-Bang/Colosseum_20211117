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

        binding.btnOk.setOnClickListener {

            val inputContent = binding.edtContent.text.toString()

//            서버에 대댓글 등록 API 호출

            ServerUtil.postRequestReReply(mContext, mReplyData.id,inputContent,object  : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

                }


            })
        }
    }

    override fun setValues() {

        mReplyData = intent.getSerializableExtra("reply") as ReplyData

        binding.txtWriterNickName.text = mReplyData.writer.nikname

        binding.txtContent.text = mReplyData.content

        getReplydetailFromServer()

        mReReplyAdapter = ReReplyAdapter(mContext, R.layout.re_reply_list_item,mReReplyadapter)


//        1. 댓글 목록 새로고침
        getReplydetailFromServer()

        runOnUiThread {

            //        2. 입력칸 비워주기

            binding.edtcontent.setText("")
            //            3. 리스트뷰를 맨 밑(20개 -> 19번 : 갯수 -1번째칸)으로 스크롤 해주기
            binding.reReplyListView.smoothScrollToPosition(mReReplyList.size -1)

        }
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