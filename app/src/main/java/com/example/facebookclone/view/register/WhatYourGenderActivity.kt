package com.example.facebookclone.view.register

import android.content.Intent
import android.graphics.RegionIterator
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.facebookclone.R
import com.example.facebookclone.model.User
import com.example.facebookclone.utils.KEY_USER
import kotlinx.android.synthetic.main.activity_what_your_gender.*
import kotlinx.android.synthetic.main.activity_what_your_name.*
import kotlinx.android.synthetic.main.activity_what_your_name.btn_next
import kotlinx.android.synthetic.main.activity_what_your_name.im_back

class WhatYourGenderActivity:AppCompatActivity() {

    private var user : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_what_your_gender)

        Log.d("atvbirthday", "onCreate: + ${intent.extras?.get(KEY_USER)}")

        user = intent.extras?.get(KEY_USER) as User

        btn_next.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable(KEY_USER,user)
            val i = Intent(this@WhatYourGenderActivity, ContactNumberActivity::class.java)
            i.putExtras(bundle)
            startActivity(i)


        }

        rb_female.setOnCheckedChangeListener { compoundButton, checked ->
            if (checked){
                rb_male.isChecked = false
                rb_custom.isChecked = false
                user?.gender=0
            }
        }

        rb_male.setOnCheckedChangeListener { compoundButton, checked ->
            if (checked){
                rb_female.isChecked = false
                rb_custom.isChecked = false
                user?.gender=1
            }
        }


        rb_custom.setOnCheckedChangeListener { compoundButton, checked ->
            if (checked){
                rb_male.isChecked = false
                rb_female.isChecked = false
                user?.gender=2
            }
        }


        im_back.setOnClickListener {
            finish()
        }
    }
}