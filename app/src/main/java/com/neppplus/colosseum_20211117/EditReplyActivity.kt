package com.neppplus.colosseum_20211117

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView

class EditReplyActivity : BaseActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBinderMapperImpl.setContentView(R.layout.activity_edit_reply)
        setValues()
        setupEvents()
    }


    override fun setupEvents() {
        TODO("Not yet implemented")
    }

    override fun setValues() {
        TODO("Not yet implemented")
    }
}