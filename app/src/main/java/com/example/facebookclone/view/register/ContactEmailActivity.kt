package com.example.facebookclone.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.facebookclone.R
import com.example.facebookclone.model.User
import com.example.facebookclone.utils.KEY_USER
import kotlinx.android.synthetic.main.activity_contact_email.*
import kotlinx.android.synthetic.main.activity_contact_email.iv_back
import kotlinx.android.synthetic.main.activity_contact_email.tv_create
import kotlinx.android.synthetic.main.activity_contact_number.*
import kotlinx.android.synthetic.main.activity_register_birthday.*
import kotlinx.android.synthetic.main.activity_register_birthday.btn_next
import kotlinx.android.synthetic.main.activity_what_your_name.*

class ContactEmailActivity : AppCompatActivity() {

    private var user : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_email)

        Log.d("chContactEmailActivity", "onCreate: + ${intent.extras?.get(KEY_USER)}")
        user = intent.extras?.get(KEY_USER) as User

        btn_next.setOnClickListener {
            user?.email= te_email.text.toString().trim()
            val atemail = te_email.text.toString().trim()
            if(atemail.isNotEmpty()){
                val bundle = Bundle()
                bundle.putSerializable(KEY_USER,user)
                val i =Intent(this@ContactEmailActivity, ChoosePasswordActivity::class.java)
                i.putExtras(bundle)
                startActivity(i)
            }else{
                Toast.makeText(this,"Please enter your email",Toast.LENGTH_SHORT).show()
            }

        }

        tv_create.setOnClickListener {
            user?.email= te_email.text.toString().trim()
            val atemail = te_email.text.toString().trim()
            if(atemail.isNotEmpty()){
                val bundle = Bundle()
                bundle.putSerializable(KEY_USER,user)
                val j = Intent( this@ContactEmailActivity, ContactNumberActivity::class.java)
                j.putExtras(bundle)
                startActivity(j)
            }else{
                Toast.makeText(this,"Please enter your email", Toast.LENGTH_SHORT).show()
            }

        }



        iv_back.setOnClickListener {
            finish()
        }
    }
}