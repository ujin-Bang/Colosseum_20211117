package com.neppplus.colosseum_20211117

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.neppplus.colosseum_20211117.adapters.ReplyAdapter
import com.neppplus.colosseum_20211117.databinding.ActivityViewTopicDetailBinding
import com.neppplus.colosseum_20211117.datas.ReplyData
import com.neppplus.colosseum_20211117.datas.TopicData
import com.neppplus.colosseum_20211117.utils.ServerUtil
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

    lateinit var binding: ActivityViewTopicDetailBinding

    lateinit var mTopicData: TopicData

    val mReplyList = ArrayList<ReplyData>()

    lateinit var mReplyAdapter : ReplyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_topic_detail)
        setupEvents()
        setValues()
    }

    override fun onResume() {
        super.onResume()

//        onResume : 이 화면이 다시 나타날 때마다 계속 실행되는 함수
//        화면에 돌아올 때마다 -> 서버에서 다시 댓글 목록도 불러오게. (자동 새로고침)
        getTopicDetailFromServer()
    }


    override fun setupEvents() {

        binding.btnWriteReply.setOnClickListener {

//            아직 투표를 안한 주제라면, 의견 작성도 막아야 함.
//            내가 투표 안 했다면, 강제종료

            if (mTopicData.mySide == null) {
                Toast.makeText(mContext, "한 진영에 투표를 해야 의견을 작성할 수 있습니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
//            했다면 어디에 투표 했는지? => mTopicData의 하위 정보(mySide)로 모두 들어있다.
//            mTopicData를 통째로 넘겨주면, 내 선택진영도 포함되어 넘어간다.

            val myIntent = Intent(mContext, EditReplyActivity::class.java)
            myIntent.putExtra("toPic",mTopicData)
            startActivity(myIntent)
        }

        binding.btnVote01.setOnClickListener {

//            첫 진영에 투표 -> 서버 API 호출 -> 이 화면 새로고침

            ServerUtil.postRequestVote(mContext, mTopicData.sideList[0].id,  object : ServerUtil.JsonResponseHandler {

                override fun onResponse(jsonObj: JSONObject) {

                    val code = jsonObj.getInt("code")

                    if (code == 200) {

//                        득표 수를 서버에서 다시 받아오자. (새로고침)
//                        득표 수? -> 토론 상세 조회 -> 선택진영 목록 -> 득표수 새로 파싱.
                        getTopicDetailFromServer()

                    }
                    else {

                        val message = jsonObj.getString("message")

                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }

                    }

                }

            })

        }

        binding.btnVote02.setOnClickListener {

            ServerUtil.postRequestVote(mContext, mTopicData.sideList[1].id, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {

                    getTopicDetailFromServer()

                }

            })

        }

    }

    override fun setValues() {

        mTopicData = intent.getSerializableExtra("topic") as TopicData

        binding.txtTopicTitle.text = mTopicData.title

        Glide.with(mContext).load(mTopicData.imageURL).into(binding.imgTopic)

//        현재 진행상황 조회 API 호출해보자. -> 토론 진영 목록 / 몇표 획득
        getTopicDetailFromServer()

        mReplyAdapter = ReplyAdapter(mContext, R.layout.reply_list_item, mReplyList)
        binding.replyListView.adapter =  mReplyAdapter

    }

    fun getTopicDetailFromServer() {

        ServerUtil.getRequestTopicDetail(mContext, mTopicData.id, "NEW" ,object : ServerUtil.JsonResponseHandler {
            override fun onResponse(jsonObj: JSONObject) {


                val dataObj = jsonObj.getJSONObject("data")
                val topicObj = dataObj.getJSONObject("topic")

//                topicObj -> 토론 주제에 대한 정보가 담긴 JSONObject -> TopicData 변환 함수의 재료로 사용.
                mTopicData = TopicData.getTopicDataFromJson(topicObj)

                runOnUiThread {
                    refreshUI()
                }

//                댓글 목록도 별도로 파싱
                val repliesArr = topicObj.getJSONArray("replies")

//                기존에 받아온 댓글은 전부 삭제
                mReplyList.clear()

                for (i  in  0  until   repliesArr.length()) {

                    val replyObj = repliesArr.getJSONObject(i)

                    val replyData = ReplyData.getReplyDataFromJson( replyObj )

                    mReplyList.add( replyData )

                }

//                서버가 더 늦게 끝났따면? 리스트뷰 내용 변경됨.

                runOnUiThread {

                    mReplyAdapter.notifyDataSetChanged()

                }


            }

        } )

    }

    fun refreshUI() {
//        mTopicData가 변경되었으면 -> 새로 반영해달라.

        binding.txtTopicTitle.text = mTopicData.title
        Glide.with(mContext).load(mTopicData.imageURL).into(binding.imgTopic)

        binding.txtReplyCount.text = "댓글 갯수 : ${mTopicData.replyCount}개"

        binding.txtSideTitle01.text = mTopicData.sideList[0].title
        binding.txtSideTitle02.text = mTopicData.sideList[1].title

        binding.txtSideVoteCount01.text = "${mTopicData.sideList[0].voteCount}표"
        binding.txtSideVoteCount02.text = "${mTopicData.sideList[1].voteCount}표"

    }


}