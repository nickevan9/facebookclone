package com.example.facebookclone.view.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.facebookclone.R
import com.example.facebookclone.model.User
import com.example.facebookclone.utils.KEY_USER
import kotlinx.android.synthetic.main.activity_contact_email.*
import kotlinx.android.synthetic.main.activity_contact_email.iv_back
import kotlinx.android.synthetic.main.activity_contact_email.tv_create
import kotlinx.android.synthetic.main.activity_contact_number.*
import kotlinx.android.synthetic.main.activity_register_birthday.*
import kotlinx.android.synthetic.main.activity_register_birthday.btn_next


class ContactNumberActivity:AppCompatActivity(){

    private var user : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_number)

        Log.d("atvgender", "onCreate: + ${intent.extras?.get(KEY_USER)}")
        user = intent.extras?.get(KEY_USER) as User


        btn_next.setOnClickListener {
            user?.phoneNumber= te_number.text.toString().trim()
            val atphoneNumber = te_number.text.toString().trim()
            if(atphoneNumber.isNotEmpty()){
                val bundle = Bundle()
                bundle.putSerializable(KEY_USER,user)

                val i = Intent(this@ContactNumberActivity, ChoosePasswordActivity::class.java)
                i.putExtras(bundle)
                startActivity(i)
            }else{
                Toast.makeText(this,"Please enter your phone number", Toast.LENGTH_SHORT).show()
            }

        }

        tv_create.setOnClickListener {
            user?.phoneNumber= te_number.text.toString().trim()
            val atphoneNumber = te_number.text.toString().trim()

            if(atphoneNumber.isNotEmpty()){
                val bundle = Bundle()
                bundle.putSerializable(KEY_USER,user)
                val j = Intent(this@ContactNumberActivity, ContactEmailActivity::class.java)
                j.putExtras(bundle)
                startActivity(j)
            }else{
                Toast.makeText(this,"Please enter your phone number", Toast.LENGTH_SHORT).show()
            }


        }

        iv_back.setOnClickListener {
            finish()
        }

        text_mobile_number.setEndIconOnClickListener {
            Log.d("hunghkp", "onCreate: tesst")
        }

        
    }
}