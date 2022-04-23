package com.example.facebookclone.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.facebookclone.R
import com.example.facebookclone.view.register.ForgotPasswordMobileActivity
import com.example.facebookclone.view.register.JoinFacebookActivity
import com.example.facebookclone.view.register.WhatYourNameActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initview()
    }

    private fun initview(){
        btn_create_account.setOnClickListener {
            val i = Intent(this, JoinFacebookActivity::class.java)
            startActivity(i)
        }
        btn_login.setOnClickListener(){

        }

        tv_forgot_password.setOnClickListener {
            val j = Intent(this, ForgotPasswordMobileActivity::class.java)
            startActivity(j)
        }
    }

}