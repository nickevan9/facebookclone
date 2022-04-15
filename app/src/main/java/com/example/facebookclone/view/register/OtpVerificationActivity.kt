package com.example.facebookclone.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.facebookclone.R
import kotlinx.android.synthetic.main.activity_otp_verification.*

class OtpVerificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)

        btn_next.setOnClickListener {
            val i = Intent(this,ChoosePasswordActivity::class.java)
            startActivity(i)
        }

        im_back.setOnClickListener {
            finish()
        }
    }

}