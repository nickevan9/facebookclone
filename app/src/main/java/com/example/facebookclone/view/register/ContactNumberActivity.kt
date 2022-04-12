package com.example.facebookclone.view.register

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.facebookclone.R
import kotlinx.android.synthetic.main.activity_contact_email.*
import kotlinx.android.synthetic.main.activity_register_birthday.*
import kotlinx.android.synthetic.main.activity_register_birthday.btn_next

class ContactNumberActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_contact_number)

        btn_next.setOnClickListener {
            val i = Intent(this@ContactNumberActivity, ChoosePasswordActivity::class.java)
            startActivity(i)
        }

        tv_create.setOnClickListener {
            val j = Intent(this@ContactNumberActivity, ContactNumberActivity::class.java)
            startActivity(j)
        }
    }
}