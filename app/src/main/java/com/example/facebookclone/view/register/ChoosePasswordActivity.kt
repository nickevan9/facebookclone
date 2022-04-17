package com.example.facebookclone.view.register

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.facebookclone.R
import com.example.facebookclone.model.User
import com.example.facebookclone.utils.KEY_USER
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_choose_password.*
import kotlinx.android.synthetic.main.activity_contact_number.*
import kotlinx.android.synthetic.main.activity_register_birthday.*
import kotlinx.android.synthetic.main.activity_register_birthday.btn_next
import kotlinx.android.synthetic.main.activity_register_birthday.im_back

class ChoosePasswordActivity:AppCompatActivity() {

    private var user : User? = null
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_password)

        Log.d("atvnummail", "onCreate: + ${intent.extras?.get(KEY_USER)}")
        user = intent.extras?.get(KEY_USER) as User

        btn_next.setOnClickListener {
            user?.password= te_password.text.toString().trim()
            val atpassword = te_password.text.toString().trim()
            if (atpassword.isNotEmpty()){
                val bundle = Bundle()
                bundle.putSerializable(KEY_USER,user)

                val i =Intent(this@ChoosePasswordActivity, TermAndPrivacyActivity::class.java)
                i.putExtras(bundle)
                startActivity(i)
            }else{
                Toast.makeText(this,"Please enter your Password", Toast.LENGTH_SHORT).show()
            }

        }

        im_back.setOnClickListener {
            finish()
        }
    }

    private fun initview(){

    }
}