package com.example.facebookclone.view.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.facebookclone.R
import kotlinx.android.synthetic.main.activity_what_your_name.*

class WhatYourNameActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_what_your_name)
        btn_next.setOnClickListener{
            val i = Intent(this@WhatYourNameActivity, RegisterBirthdayActivity::class.java)
            startActivity(i)
        }
    }
}