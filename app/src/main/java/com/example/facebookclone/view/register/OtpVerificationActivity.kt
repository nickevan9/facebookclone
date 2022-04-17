package com.example.facebookclone.view.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.facebookclone.R
import com.example.facebookclone.model.User
import com.example.facebookclone.utils.KEY_USER
import com.example.facebookclone.utils.KEY_VERIFIED_ID
import com.example.facebookclone.view.dialog.LoadingDialog
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_otp_verification.*

class OtpVerificationActivity : AppCompatActivity() {

    private var user: User? = null
    private lateinit var auth: FirebaseAuth

    private var verifiedID : String ? = null
    private var loadingDialog : LoadingDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)

        verifiedID = intent.getStringExtra(KEY_VERIFIED_ID)
        user = intent.extras?.get(KEY_USER) as User

        initFirebase()

        initView()


    }

    private fun initView(){
        loadingDialog = LoadingDialog(this)
        btn_next.setOnClickListener {
            loadingDialog?.showDialog()
            if (ed_otp.text.toString().isNotEmpty() && ed_otp.text.toString().length == 6 && verifiedID != null){
                auth.signInWithCredential(PhoneAuthProvider.getCredential(verifiedID!!,ed_otp.text.toString()))
                    .addOnCompleteListener(
                        this
                    ) {
                        if (it.isSuccessful) {
                            val bundle = Bundle()
                            bundle.putSerializable(KEY_USER, user)
                            val i = Intent(this, ChoosePasswordActivity::class.java)
                            i.putExtras(bundle)
                            loadingDialog?.dismissDialog()
                            startActivity(i)
                        } else {
                            // Show Error
                            loadingDialog?.dismissDialog()
                            if (it.exception is FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                showSnackBar(it.exception?.message?: "Verification Failed")
                            } else {
                                showSnackBar("Verification Failed")
                            }
                        }
                    }
            }
        }

        im_back.setOnClickListener {
            finish()
        }
    }


    private fun initFirebase() {
        auth = Firebase.auth
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(container, message, Snackbar.LENGTH_LONG).show()
    }

}