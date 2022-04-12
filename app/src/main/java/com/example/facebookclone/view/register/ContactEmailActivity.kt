package com.example.facebookclone.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.facebookclone.R
import kotlinx.android.synthetic.main.activity_contact_email.*
import kotlinx.android.synthetic.main.activity_register_birthday.*
import kotlinx.android.synthetic.main.activity_register_birthday.btn_next

class ContactEmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_email)

        btn_next.setOnClickListener {
            val i =Intent(this@ContactEmailActivity, ChoosePasswordActivity::class.java)
            startActivity(i)
        }

        tv_create.setOnClickListener {
            val j = Intent( this@ContactEmailActivity, ContactNumberActivity::class.java)
            startActivity(j)
        }
    }
}