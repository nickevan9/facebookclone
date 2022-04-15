package com.example.facebookclone.view.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.facebookclone.R
import com.example.facebookclone.model.User
import com.example.facebookclone.utils.KEY_USER
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_contact_email.*
import kotlinx.android.synthetic.main.activity_contact_email.iv_back
import kotlinx.android.synthetic.main.activity_contact_email.tv_create
import kotlinx.android.synthetic.main.activity_contact_number.*
import kotlinx.android.synthetic.main.activity_register_birthday.btn_next
import java.util.concurrent.TimeUnit


class ContactNumberActivity : AppCompatActivity() {

    private var user: User? = null

    private lateinit var auth: FirebaseAuth
    private var storedVerificationId: String? = ""
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_number)

        user = intent.extras?.get(KEY_USER) as User

        initFirebase()

        initView()

    }

    private fun initView() {
        btn_next.setOnClickListener {
            user?.phoneNumber = te_number.text.toString().trim()
            val atphoneNumber = te_number.text.toString().trim()
//            if(atphoneNumber.isNotEmpty()){
//                val bundle = Bundle()
//                bundle.putSerializable(KEY_USER,user)
//
//                val i = Intent(this@ContactNumberActivity, OtpVerificationActivity::class.java)
//                i.putExtras(bundle)
//                startActivity(i)
//            }else{
//                Toast.makeText(this,"Please enter your phone number", Toast.LENGTH_SHORT).show()
//            }

            startPhoneNumberVerification(te_number.text.toString().trim())
        }

        tv_create.setOnClickListener {
            user?.phoneNumber = te_number.text.toString().trim()
            val atphoneNumber = te_number.text.toString().trim()

            if (atphoneNumber.isNotEmpty()) {
                val bundle = Bundle()
                bundle.putSerializable(KEY_USER, user)
                val j = Intent(this@ContactNumberActivity, ContactEmailActivity::class.java)
                j.putExtras(bundle)
                startActivity(j)
            } else {
                Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show()
            }


        }

        iv_back.setOnClickListener {
            finish()
        }
    }

    private fun initFirebase() {
        auth = Firebase.auth

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Log.d("hunghkp", "onVerificationCompleted: ")
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Log.d("hunghkp", "onVerificationFailed: ")
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                Log.d("hunghkp", "onCodeSent: $p0")
            }

            override fun onCodeAutoRetrievalTimeOut(p0: String) {
                super.onCodeAutoRetrievalTimeOut(p0)
                Log.d("hunghkp", "onCodeAutoRetrievalTimeOut: ")
            }

        }
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(120L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}