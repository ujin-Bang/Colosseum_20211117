package com.neppplus.colosseum_20211117

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neppplus.colosseum_20211117.databinding.ActivityViewReplyDetailBinding
import com.neppplus.colosseum_20211117.datas.ReplyData

class ViewReplyDetailActivity : BaseActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding : ActivityViewReplyDetailBinding
        lateinit var

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
    }
}