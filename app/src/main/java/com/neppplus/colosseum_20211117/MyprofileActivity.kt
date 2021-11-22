package com.neppplus.colosseum_20211117

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MyprofileActivity : BaseActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myprofile)
        setValues()
        setupEvents()
    }


    override fun setupEvents() {


        binding.btnLogout.setOnClickLister {

//            찐짜 로그아웃 할건지? 확인
             val alert = AlerDialog.Buler(mContext)
            alert.setTitle("로그아웃 확인")
            alert.setMassage("정말 로그아웃 하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->

//                실제 로그아웃 처리

            })
            alert.setNegativeButton("취소",null)
            alert.show()

        }
    }

    override fun setValues() {


    }
}