package com.neppplus.colosseum_20211117

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import com.neppplus.colosseum_20211117.datas.TopicData
import com.neppplus.colosseum_20211117.utils.ServerUtil
import org.json.JSONObject

class EditReplyActivity : BaseActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBinderMapperImpl.setContentView(R.layout.activity_edit_reply)
        setValues()
        setupEvents()
    }


    override fun setupEvents() {

        bindig.btnOk.setOnClickListener {
//            입력한 문구가 몇 글자? -> 10글자 이내면 거부

            val inputContent = binding.edtContent.text.toString()
            if(inputContent.lenth < 10) {
                Toast.makeText(mContext, "최소 10글자 이사 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }


//            검사 통과 -> 의견 등록( 서버호출)
            ServerUtil.postRequestTopicReply(mContext,mTopicdata.id, inputContent,object : ServerUtil.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {

                    val code = jsonObj.getInt("code")

                    runOnUiThread {
                        if(code == 200 ) {
                            Toast.makeText(mContext, "의견 등록에 성공했습니다", Toast.LENGTH_SHORT).show()

                        }
                        else {

                            val message = jsonObj.getString("message")
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }

                    }

                }


            })
        }
    }

    override fun setValues() {

        mTopicData = Intent.getSerialIzableExtra("topic") as TopicData

        binding.txtTopicTitle.text = mTopicData.title

        binding.txtMySideTitlr.text = mTopicData.mySide!!.title
    }
}