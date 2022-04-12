package com.example.facebookclone.view.register

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.facebookclone.R
import kotlinx.android.synthetic.main.activity_register_birthday.*

class TermAndPrivacyActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_terms_privacy)
    }
}