package com.neppplus.colosseum_20211117

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.neppplus.colosseum_20211117.datas.TopicData

class EditReplyActivity : BaseActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBinderMapperImpl.setContentView(R.layout.activity_edit_reply)
        setValues()
        setupEvents()
    }


    override fun setupEvents() {
    }

    override fun setValues() {

        mTopicData = Intent.getSerialIzableExtra("topic") as TopicData

        binding.txtTopicTitle.text = mTopicData.title

        binding.txtMySideTitlr.text = mTopicData.mySide!!.title
    }
}