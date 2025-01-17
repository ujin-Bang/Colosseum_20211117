package com.neppplus.colosseum_20211117

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neppplus.colosseum_20211117.utils.ContextUtil

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

//                실제 로그아웃 처리 => 저장도니 토큰을 ""으로 돌려주자 ( 내 폰에 저장된 토큰 삭제)

                ContextUtil.setToken(mContext,"")

//                화면 종료 -> (열려있는 모든 화면을 전부 닫고) SplashActivity로 이동

                val myIntent = Intent(mContext,SplashActivity::class.java)
                myIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(myIntent)

                finish()

            })
            alert.setNegativeButton("취소",null)
            alert.show()

        }
    }

    override fun setValues() {


    }
}