package com.neppplus.colosseum_20211117

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neppplus.colosseum_20211117.databinding.ActivityViewReplyDetailBinding

class ViewReplyDetailActivity : BaseActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_reply_detail)

        lateinit var binding : ActivityViewReplyDetailBinding
        setValues()
        setupEvents()
    }


    override fun setupEvents() {
    }

    override fun setValues() {
    }
}