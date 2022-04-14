package com.example.facebookclone.view.register

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.facebookclone.R
import com.example.facebookclone.model.User
import com.example.facebookclone.utils.KEY_USER
import kotlinx.android.synthetic.main.activity_register_birthday.*
import kotlinx.android.synthetic.main.activity_register_birthday.im_back
import kotlinx.android.synthetic.main.activity_terms_privacy.*

class TermAndPrivacyActivity : AppCompatActivity(){
    private var user : User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_privacy)

        Log.d("atvpassword", "onCreate: + ${intent.extras?.get(KEY_USER)}")
        user = intent.extras?.get(KEY_USER) as User

        btn_sign_up.setOnClickListener {

        }

        im_back.setOnClickListener {
            finish()
        }
    }
}